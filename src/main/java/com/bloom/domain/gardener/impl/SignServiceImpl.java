package com.bloom.domain.gardener.impl;

import com.bloom.dao.ext.GardenerExtDao;
import com.bloom.dao.po.Gardener;
import com.bloom.domain.CachedName;
import com.bloom.domain.gardener.GardenerWechatOpenIdService;
import com.bloom.domain.gardener.RoleService;
import com.bloom.domain.gardener.SignService;
import com.bloom.domain.gardener.meta.Gender;
import com.bloom.domain.gardener.meta.SessionConstantKey;
import com.bloom.exception.*;
import com.bloom.util.encrypt.GardenerEncrypt;
import com.bloom.web.gardener.vo.SignUpForm;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Optional;
/**
 * SignUp、SignIn、SignOut
 * @author 83554
 *
 */

@Service
public class SignServiceImpl implements SignService{
	@Resource
	private RoleService roleService;
	@Resource
	private GardenerExtDao gardenerExtDao;
	@Resource
	private GardenerWechatOpenIdService gardenerWechatOpenIdServiceImpl;

	/**
	 * 注册
	 * @param signUpForm
	 */
	@Transactional
	@Override
	public Gardener signUp(SignUpForm signUpForm) {
		String originalUsername = signUpForm.getUsername();
		String originalPassword = signUpForm.getPassword();
		Date now = new Date();
		Optional<Integer> keyOpt = Optional.ofNullable(
				gardenerExtDao.selectKeyByUsername(GardenerEncrypt.encryptUsername(originalUsername))
				);
		if (keyOpt.isPresent())
			throw new ServiceException("该用户名已存在！");
		// 注册
		Gardener gardener = new Gardener();
		gardener.setUsername(GardenerEncrypt.encryptUsername(originalUsername));
		gardener.setPassword(originalPassword);
		gardener.setNickName(signUpForm.getNickName());
		gardener.setEmail(signUpForm.getEmail());
		gardener.setGender(Gender.保密.name());
		gardener.setRoleId(roleService.defaultRole().getId());
		gardenerExtDao.insert(gardener);
		gardener.setPassword(GardenerEncrypt.encryptPassword(gardener.getId(), originalUsername, originalPassword));
		gardener.setCt(now);
		gardener.setUt(now);
		gardenerExtDao.updateByPrimaryKeySelective(gardener);
		return gardener;
	}

	/**
	 * 登录
	 * @param request
	 * @param originalUsername
	 * @param originalPassword
	 * @return
	 */
	@Override
	@CachePut(cacheNames = CachedName.GARDENERS, key = "#result.id")
	public Gardener signIn(HttpServletRequest request, String originalUsername,String originalPassword) {
		Integer key = Optional.ofNullable(
				gardenerExtDao.selectKeyByUsername(GardenerEncrypt.encryptUsername(originalUsername))
				)
				.orElseThrow(() -> new IncorrectAccountException());
		String password = GardenerEncrypt.encryptPassword(key, originalUsername, originalPassword);
		Gardener gardener = gardenerExtDao.selectByPrimaryKey(key);
		if (! password.equals(gardener.getPassword())) {
			System.out.println(password + gardener.getPassword());
			throw new IncorrectPasswordException();
		}
		
		WebUtils.setSessionAttribute(request, SessionConstantKey.GARDENER_ID_KEY, gardener.getId());
		WebUtils.setSessionAttribute(request, SessionConstantKey.ROLE_ID_KEY, gardener.getRoleId());
		
		return gardener;
	}
	/**
	 * 微信登录
	 * @throws WechatException 
	 */
	@Override
	@CachePut(cacheNames = CachedName.GARDENERS, key = "#result.id")
	public Gardener signInByWechatOpenId(HttpServletRequest request, String appId, String openId){
		int gardenerId = gardenerWechatOpenIdServiceImpl.getGardenerIdByWechatOpenId(appId, openId)
				.orElseThrow(() -> new WechatNoBindGrasswortAccountException(appId, openId));

		System.out.println(gardenerId);
		Gardener gardener = gardenerExtDao.selectByPrimaryKey(gardenerId);
		
		WebUtils.setSessionAttribute(request, SessionConstantKey.GARDENER_ID_KEY, gardener.getId());
		WebUtils.setSessionAttribute(request, SessionConstantKey.ROLE_ID_KEY, gardener.getRoleId());
		
		return gardener;
	}

	/**
	 * 登出
	 * @param request
	 */
	@Override
	public void signOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute(SessionConstantKey.GARDENER_ID_KEY);
		session.removeAttribute(SessionConstantKey.ROLE_ID_KEY);
		session.invalidate();
	}
	
}

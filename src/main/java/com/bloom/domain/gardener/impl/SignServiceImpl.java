package com.bloom.domain.gardener.impl;

import java.util.Date;
import java.util.Optional;

import javax.annotation.Resource;
import javax.management.ConstructorParameters;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.WebUtils;

import com.beust.jcommander.Parameters;
import com.beust.jcommander.SubParameter;
import com.bloom.annotation.RoleCheck;
import com.bloom.dao.ext.GardenerExtDao;
import com.bloom.dao.po.Gardener;
import com.bloom.domain.gardener.RoleService;
import com.bloom.domain.gardener.SignService;
import com.bloom.domain.gardener.meta.Gender;
import com.bloom.domain.gardener.meta.HighGradeRole;
import com.bloom.domain.gardener.meta.SessionConstantKey;
import com.bloom.exception.FlowBreakException;
import com.bloom.util.encrypt.GardenerEncrypt;
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
	/**
	 * SignUp
	 * @param originalUsername
	 * @param originalPassword
	 */
	@Transactional
	@Override
	public void signUp(String originalUsername,String originalPassword) {
		Date now = new Date();
		Optional<Integer> keyOpt = Optional.ofNullable(
				gardenerExtDao.selectKeyByUsername(GardenerEncrypt.encryptUsername(originalUsername))
				);
		if(keyOpt.isPresent()) 
			throw new FlowBreakException("该用户名已存在！");
		//注册
		Gardener gardener = new Gardener();
		gardener.setUsername(GardenerEncrypt.encryptUsername(originalUsername));
		gardener.setPassword(originalPassword);
		gardener.setGender(Gender.保密.name());
		gardener.setRoleId(roleService.defaultRole().getId());
		gardenerExtDao.insert(gardener);
		gardener.setPassword(GardenerEncrypt.encryptPassword(gardener.getId(), originalUsername, originalPassword));
		gardener.setCt(now);
		gardener.setUt(now);
		gardenerExtDao.updateByPrimaryKeySelective(gardener);
	}
	/**
	 * SignIn
	 * @param originalUsername
	 * @param originalPassword
	 */
	@Override
	public Gardener signIn(HttpServletRequest request,String originalUsername,String originalPassword) {
		Integer key = Optional.ofNullable(
				gardenerExtDao.selectKeyByUsername(GardenerEncrypt.encryptUsername(originalUsername))
				)
				.orElseThrow(() -> new FlowBreakException("该账户名不存在！"));
		String password = GardenerEncrypt.encryptPassword(key, originalUsername, originalPassword);
		Gardener gardener = gardenerExtDao.selectByPrimaryKey(key);
		if(!password.equals(gardener.getPassword()))
			throw new FlowBreakException("登录失败！密码有误！");
		WebUtils.setSessionAttribute(request, SessionConstantKey.GARDENER_ID_KEY, gardener.getId());
		WebUtils.setSessionAttribute(request, SessionConstantKey.ROLE_ID_KEY, gardener.getRoleId());
		return gardener;
	}
	/**
	 * SignOut
	 * @param session
	 */
	@Override
	public void signOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute(SessionConstantKey.GARDENER_ID_KEY);
		session.removeAttribute(SessionConstantKey.ROLE_ID_KEY);
		session.invalidate();
	}
}
/**
 * 
 */
package com.bloom.domain.gardener.impl;

import com.bloom.dao.po.Gardener;
import com.bloom.dao.po.Role;
import com.bloom.domain.gardener.RoleService;
import com.bloom.domain.gardener.meta.SessionConstantKey;
import com.bloom.exception.IncorrectAccountException;
import com.bloom.exception.IncorrectPasswordException;
import com.bloom.exception.WechatNoBindGrasswortAccountException;
import com.bloom.web.gardener.vo.SignUpForm;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * <p>Title: SignServiceImplTest2.java<／p>
 * <p>Description: SignServiceImpl测试类<／p>
 * <p>Copyright: Copyright (c) 2019<／p>
 * <p>Company: grasswort<／p>
 *
 * @author 树林里面丢了鞋
 * @date 2018年12月24日
 * @version 1.0
*/
@RunWith(MockitoJUnitRunner.class)  
public class SignServiceImplTest{
	/**
	 * 测试真实对象
	 */
	@InjectMocks private SignServiceImpl signServiceImpl;
	/**
	 * Dao层替身（以间谍形式注入自定义模拟对象，进行局部打桩）
	 */
	@Spy private GardenerExtDaoMock gardenerExtDao;
	/**
	 * 角色服务替身
	 */
	@Mock private RoleService roleService;
	/**
	 * request替身
	 */
	@Mock private HttpServletRequest request;
	/**
	 * session替身
	 */
	@Mock private HttpSession session;
	/**
	 * 公众号与grasswort账户映射替身
	 */
	@Mock private GardenerWechatOpenIdServiceImpl gardenerWechatOpenIdServiceImpl;
	/**
	 * 
	 * <p>Title: testSignUpAndSignIn</p>
	 * <p>Description: 测试注册并登陆</p>
	 * void
	 */
	@Test
	public void testSignUpAndSignIn() {
		// 录制默认角色ID为1
		final Role defaultRole = new Role();
		defaultRole.setId(1);
		when(roleService.defaultRole()).thenReturn(defaultRole);
		// 预设request获取session为模拟session
		when(request.getSession()).thenReturn(session);
		
		// 准备数据
		final String USERNAME = "grasswort";
		final String PASSWORD = "grasswort";
		SignUpForm form = new SignUpForm();
		form.setUsername(USERNAME);
		form.setPassword(PASSWORD);
		form.setNickName("树林里面丢了鞋");
		form.setEmail("835547206@qq.com");
		
		// 注册 + 登陆
		signServiceImpl.signUp(form);
		
		try {
			signServiceImpl.signIn(request, USERNAME + RandomStringUtils.random(1), PASSWORD);
			fail("没有抛出账号错误异常!");
		} catch (IncorrectAccountException e) {
			assertTrue(true);
		}
		
		try {
			signServiceImpl.signIn(request, USERNAME, PASSWORD + RandomStringUtils.random(1));
			fail("没有抛出密码错误异常!");
		} catch (IncorrectPasswordException e) {
			assertTrue(true);
		}
		
		Gardener gardener = signServiceImpl.signIn(request, USERNAME, PASSWORD);
		
		// 校验登陆成功
		assertNotNull(gardener.getId());
		verify(session, times(1)).setAttribute(SessionConstantKey.GARDENER_ID_KEY, gardener.getId());
		verify(session, times(1)).setAttribute(SessionConstantKey.ROLE_ID_KEY, gardener.getRoleId());
	}

	/**
	 * 
	 * <p>Title: testSignInByWechatOpenIdButNoMapGrasswortAccount</p>
	 * <p>Description: 测试在微信尚未绑定grasswort账户场景登陆</p>
	 * void
	 */
	@Test(expected = WechatNoBindGrasswortAccountException.class)
	public void testSignInByWechatOpenIdButNoMapGrasswortAccount() {
		when(gardenerWechatOpenIdServiceImpl.getGardenerIdByWechatOpenId(anyString(), anyString()))
			.thenReturn(Optional.empty());
		
		signServiceImpl.signInByWechatOpenId(request, RandomStringUtils.random(6), RandomStringUtils.random(6));
	}
	/**
	 * 
	 * <p>Title: testSignInByWechatOpenId</p>
	 * <p>Description: 测试微信已绑定grasswort账户场景登陆</p>
	 * void
	 */
	@Test
	public void testSignInByWechatOpenId() {
		final int GARDENER_ID = 1, DEFAULT_ROLE_ID = 1;
		// 无论任何参数都返回 1
		when(gardenerWechatOpenIdServiceImpl.getGardenerIdByWechatOpenId(anyString(), anyString()))
			.thenReturn(Optional.of(GARDENER_ID));
		when(request.getSession()).thenReturn(session);
		
		// 准备数据
		Gardener gardener = new Gardener();
		gardener.setId(GARDENER_ID);
		gardener.setRoleId(DEFAULT_ROLE_ID);
		gardenerExtDao.insert(gardener);
		// 执行
		Gardener loginGardener = signServiceImpl.signInByWechatOpenId(request, RandomStringUtils.random(6), RandomStringUtils.random(6));
		// 校验
		assertEquals(gardener.getId(), loginGardener.getId());
		verify(session, times(1)).setAttribute(SessionConstantKey.GARDENER_ID_KEY, gardener.getId());
		verify(session, times(1)).setAttribute(SessionConstantKey.ROLE_ID_KEY, gardener.getRoleId());
	}
	/**
	 * 
	 * <p>Title: testSignOut</p>
	 * <p>Description: 测试登出</p>
	 * void
	 */
	@Test
	public void testSignOut() {
		when(request.getSession()).thenReturn(session);
		
		signServiceImpl.signOut(request);
		
		verify(session, times(1)).removeAttribute(SessionConstantKey.GARDENER_ID_KEY);
		verify(session, times(1)).removeAttribute(SessionConstantKey.ROLE_ID_KEY);
		verify(session, times(2)).removeAttribute(anyString());
		verify(session, times(1)).invalidate();
	}

}

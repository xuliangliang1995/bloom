/**
 * 
 */
package com.bloom.domain.gardener;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.bloom.dao.po.Gardener;
import com.bloom.dao.po.Role;
import com.bloom.domain.gardener.impl.SignServiceImpl;
import com.bloom.web.gardener.vo.SignUpForm;

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
	 * 测试对象
	 */
	@InjectMocks private SignServiceImpl signServiceImpl;
	/**
	 * 模拟Dao层对象
	 */
	@Spy private GardenerExtDaoMock gardenerExtDao;
	/**
	 * 模拟角色服务
	 */
	@Mock private RoleService roleService;
	/**
	 * Test method for {@link com.bloom.domain.gardener.impl.SignServiceImpl#signUp(com.bloom.web.gardener.vo.SignUpForm)}.
	 */
	@Test
	public void testSignUpAndSignIn() {
		/// 录制模拟对象行为(之前Mock gardenerExtDao时的代码，留作参考)
		/*// 1、根据用户名（加密后）从数据库中查询对象，永远返回null
		when(gardenerExtDao.selectKeyByUsername(anyString())).thenReturn(null);
		// 2、向数据库中插入对象，id永远为1
		when(gardenerExtDao.insert(any(Gardener.class))).thenAnswer(new Answer<Integer>() {
			public Integer answer(InvocationOnMock invocation) {
				Gardener gardener = invocation.getArgument(0);
				gardener.setId(1);
		         return 1;
		     }
		});*/
		// 录制默认角色ID为1
		final Role defaultRole = new Role();
		defaultRole.setId(1);
		when(roleService.defaultRole()).thenReturn(defaultRole);
		// 准备数据
		final String USERNAME = "grasswort";
		final String PASSWORD = "grasswort";
		SignUpForm form = new SignUpForm();
		form.setUsername(USERNAME);
		form.setPassword(PASSWORD);
		form.setNickName("树林里面丢了鞋");
		form.setEmail("835547206@qq.com");
		// 注册
		signServiceImpl.signUp(form);
		// 登录
		Gardener gardener = signServiceImpl.signIn(USERNAME, PASSWORD);
		assertNotNull(gardener);
		assertNotNull(gardener.getId());
	}

	/**
	 * Test method for {@link com.bloom.domain.gardener.impl.SignServiceImpl#signInByWechatOpenId(javax.servlet.http.HttpServletRequest, java.lang.String, java.lang.String)}.
	 */
	@Ignore
	public void testSignInByWechatOpenId() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.bloom.domain.gardener.impl.SignServiceImpl#signOut(javax.servlet.http.HttpServletRequest)}.
	 */
	@Ignore
	public void testSignOut() {
		fail("Not yet implemented");
	}

}

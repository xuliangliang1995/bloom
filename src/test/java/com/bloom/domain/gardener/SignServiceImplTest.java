/**
 * 
 */
package com.bloom.domain.gardener;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.bloom.BaseJunitTest;
import com.bloom.dao.po.Gardener;
import com.bloom.exception.IncorrectAccountException;
import com.bloom.exception.IncorrectPasswordException;
import com.bloom.exception.ServiceException;
import com.bloom.web.gardener.vo.SignUpForm;

/**
 * <p>Title: SignServiceImplTest.java<／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2019<／p>
 * <p>Company: grasswort<／p>
 *
 * @author 树林里面丢了鞋
 * @date 2018年12月23日
 * @version 1.0
*/
public class SignServiceImplTest extends BaseJunitTest{
	@Resource
	private SignService signServiceImpl;
	
	private String username;
	private String password;
	private String email;
	private String nickName;
	private SignUpForm form;
	
	@Before
	public void setUp() {
		while (true) {
			username = RandomStringUtils.randomNumeric(6);
			password = RandomStringUtils.randomNumeric(8);
			try {
				signServiceImpl.signIn(username, password);
			} catch (ServiceException e) {
				break;
			}
		}
		email = "835547206@qq.com";
		nickName = "shyCode";
		form = new SignUpForm();
		form.setUsername(username);
		form.setPassword(password);
		form.setNickName(nickName);
		form.setEmail(email);
	}
	
	/**
	 * 
	 * <p>Title: testSignUpAndSignIn</p>
	 * <p>Description: 测试注册和登录</p>
	 * void
	 */
	@Test
	@Rollback(value = true)
	@Transactional
	public void testSignUpAndSignIn() {
		this.signUp();
		Gardener gardener = signServiceImpl.signIn(username, password);
		assertNotNull(gardener);
	}
	
	/**
	 * 
	 * <p>Title: testSignUpAndSignInWithIncorrectUsername</p>
	 * <p>Description: 错误账号登录</p>
	 * void
	 */
	@Test(expected = IncorrectAccountException.class)
	@Rollback(value = true)
	@Transactional
	public void testSignUpAndSignInWithIncorrectUsername() {
		this.signUp();
		signServiceImpl.signIn(username + password, password);
	}
	
	/**
	 * 
	 * <p>Title: testSignUpAndSignInWithIncorrectPassword</p>
	 * <p>Description: 错误密码登录</p>
	 * void
	 */
	@Test(expected = IncorrectPasswordException.class)
	@Rollback(value = true)
	@Transactional
	public void testSignUpAndSignInWithIncorrectPassword() {
		this.signUp();
		signServiceImpl.signIn(username , username + password);
	}
	
	@After
	public void after() {
		username = null;
		password = null;
		email = null;
		nickName = null;
		form = null;
	}
	
	/**
	 * 
	 * <p>Title: signUp</p>
	 * <p>Description: 注册</p>
	 * void
	 */
	private void signUp() {
		signServiceImpl.signUp(form);
	}
}

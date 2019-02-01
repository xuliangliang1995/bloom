package com.bloom.domain.gardener.impl;

import com.bloom.SpringTestContext;
import com.bloom.dao.po.Gardener;
import com.bloom.domain.gardener.general.LoginCheckUtil;
import com.bloom.domain.gardener.meta.SessionConstantKey;
import com.bloom.exception.IncorrectAccountException;
import com.bloom.exception.IncorrectPasswordException;
import com.bloom.exception.ServiceException;
import com.bloom.web.gardener.vo.SignUpForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * 注册、登录单元测试
 * {@link SignServiceImpl}
 */
public class SignServiceImplTest extends SpringTestContext {
	@Resource
	private SignServiceImpl signServiceImpl;
	@Resource
	private void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Autowired
	private MockHttpServletRequest request;
	@Autowired
	private GardenerWechatOpenIdServiceImpl gardenerWechatOpenIdServiceImpl;

	private JdbcTemplate jdbcTemplate;

	private static final SignUpForm signUpForm = new SignUpForm();
	/**
	 * 数据库中不存在的 登录账号
	 */
	private static final String USERNAME_NOT_EXISTS = "unit_username_not_exists";

	@Before
	public void setUp() {
		signUpForm.setNickName("unit_nickname");
		signUpForm.setUsername("unit_username");
		signUpForm.setPassword("unit_password");
		signUpForm.setEmail("835547206@qq.com");
	}

	@After
	public void tearDown() {}

	/**
	 * 测试注册登录流程（感觉此类以类为单元进行测试更为合适）
	 */
	@Test
	@Transactional
	public void testSignUpAndSignIn() {
		final int PREVIOUS_GARDENER_COUNT = JdbcTestUtils.countRowsInTable(jdbcTemplate, "gardener");
		// 注册
		Gardener gardener = signServiceImpl.signUp(signUpForm);

		// 验证
		assertEquals(signUpForm.getNickName(), gardener.getNickName());
		assertEquals(PREVIOUS_GARDENER_COUNT + 1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "gardener"));


		try {
			signServiceImpl.signUp(signUpForm);
			fail("尝试重复注册，但并没有抛出异常。");
		} catch (ServiceException e) {
			assertTrue(true);
		}

		try {
			signServiceImpl.signIn(request, USERNAME_NOT_EXISTS, "password");
			fail("尝试使用不存在的账户登录，但并没有抛出账户不存在异常。");
		} catch (IncorrectAccountException e) {
			assertTrue(true);
		}

		try {
			signServiceImpl.signIn(request, signUpForm.getUsername(), signUpForm.getPassword().concat("error"));
			fail("尝试使用错误面登录，但并没有抛出密码错误异常。");
		} catch (IncorrectPasswordException e) {
			assertTrue(true);
		}


		final Integer REGISTER_GARDENER_ID = gardener.getId();
		// 登录
		Gardener loginGardener = signServiceImpl.signIn(request, signUpForm.getUsername(), signUpForm.getPassword());
		final Integer LOGIN_GARDENER_ID = loginGardener.getId();
		// 验证
		assertTrue(LoginCheckUtil.loginCheck(request));
		assertEquals(REGISTER_GARDENER_ID, LOGIN_GARDENER_ID);
		assertEquals(signUpForm.getNickName(), gardener.getNickName());

		// 登出
		signServiceImpl.signOut(request);

		assertFalse(LoginCheckUtil.loginCheck(request));

		// 微信登录
		GardenerWechatOpenIdServiceImpl mockService = Mockito.mock(GardenerWechatOpenIdServiceImpl.class);
		when(mockService.getGardenerIdByWechatOpenId(anyString(), anyString()))
				.thenReturn(Optional.of(REGISTER_GARDENER_ID));

		signServiceImpl.setGardenerWechatOpenIdServiceImpl(mockService);
		signServiceImpl.signIn(request, signUpForm.getUsername(), signUpForm.getPassword());

		assertTrue(LoginCheckUtil.loginCheck(request));
		assertEquals(REGISTER_GARDENER_ID, Integer.valueOf(LoginCheckUtil.loginGardenerId(request)));

		signServiceImpl.setGardenerWechatOpenIdServiceImpl(gardenerWechatOpenIdServiceImpl);

	}

	/**
	 * {@link SignServiceImpl#signUp(SignUpForm)}
	 */
	@Test
	@Ignore
	@Transactional
	public void signUp() {
		final int PREVIOUS_GARDENER_COUNT = JdbcTestUtils.countRowsInTable(jdbcTemplate, "gardener");
		Gardener gardener = signServiceImpl.signUp(signUpForm);
		assertNotNull(gardener.getId());
		assertEquals("unit_nickname", gardener.getNickName());
		assertEquals(PREVIOUS_GARDENER_COUNT + 1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "gardener"));
	}

	/**
	 * {@link SignServiceImpl#signIn(HttpServletRequest, String, String)}
	 */
	@Test
	@Ignore
	@Transactional
	public void signIn() {
		signServiceImpl.signUp(signUpForm);
		Gardener gardener = signServiceImpl.signIn(request, signUpForm.getUsername(), signUpForm.getPassword());

		assertTrue(LoginCheckUtil.loginCheck(request));
		assertEquals("unit_nickname", gardener.getNickName());
	}

	/**
	 * {@link SignServiceImpl#signInByWechatOpenId(HttpServletRequest, String, String)}
	 */
	@Test
	@Ignore
	@Transactional
	public void signInByWechatOpenId() {
		fail("yet not implement");
	}

	/**
	 * {@link SignServiceImpl#signOut(HttpServletRequest)}
	 */
	@Test
	@Ignore
	public void signOut() {
		WebUtils.setSessionAttribute(request, SessionConstantKey.GARDENER_ID_KEY, 1);
		WebUtils.setSessionAttribute(request, SessionConstantKey.ROLE_ID_KEY, 1);
		signServiceImpl.signOut(request);

		assertFalse(LoginCheckUtil.loginCheck(request));
	}
}
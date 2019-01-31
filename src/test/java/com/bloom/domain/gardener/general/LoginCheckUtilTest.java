package com.bloom.domain.gardener.general;

import com.bloom.domain.gardener.MockitoContext;
import com.bloom.domain.gardener.meta.SessionConstantKey;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * <p>Title: LoginCheckUtilTest.java<／p>
 * <p>Description: LoginCheckUtil工具测试<／p>
 * <p>Copyright: Copyright (c) 2019<／p>
 * <p>Company: grasswort<／p>
 *
 * @author 树林里面丢了鞋
 * @version 1.0
 * @date 2019/1/27
 * {@link LoginCheckUtil}
 */
public class LoginCheckUtilTest extends MockitoContext {

	private MockHttpServletRequest request;

	@Before
	public void setUp() {
		request = new MockHttpServletRequest();
	}
	/**
	 * {@link LoginCheckUtil#loginGardenerId(HttpServletRequest)}
	 */
	@Test
	public void testLoginGardenerId() {
		WebUtils.setSessionAttribute(request, SessionConstantKey.GARDENER_ID_KEY, 1);
		assertEquals(1, LoginCheckUtil.loginGardenerId(request));
	}

	/**
	 * {@link LoginCheckUtil#loginCheck(HttpServletRequest)}
	 */
	@Test
	public void testLoginCheck() {
		WebUtils.setSessionAttribute(request, SessionConstantKey.GARDENER_ID_KEY,1);
		assertTrue(LoginCheckUtil.loginCheck(request));
	}

	/**
	 * {@link LoginCheckUtil#loginRoleId(HttpServletRequest)}
	 */
	@Test
	public void testLoginRoleId() {
		WebUtils.setSessionAttribute(request, SessionConstantKey.ROLE_ID_KEY, 1);
		assertEquals(1, LoginCheckUtil.loginRoleId(request));
	}

	@After
	public void tearDown() {
		request = null;
	}
}
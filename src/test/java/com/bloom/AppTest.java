package com.bloom;

import com.bloom.domain.gardener.SignService;
import com.bloom.exception.ServiceException;
import com.bloom.web.gardener.vo.SignUpForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextHierarchy({
		@ContextConfiguration(value = "classpath:spring-context-test.xml")
})
@TestPropertySource(value = {
		"classpath:config/application-test.properties"
},properties = {
		"spring.test.context.cache.maxSize=32"
})
public class AppTest {
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Before
	public void setUp() {
		signUpForm = new SignUpForm();
		signUpForm.setNickName("昵称");
		signUpForm.setUsername("nickName");
		signUpForm.setPassword("my_password");
		signUpForm.setEmail("835547206@qq.com");
	}

	/**
	 * 注册
	 */
	@Test
	@Transactional
	@Rollback
	public void testSignUp1() {
		final int previousCount = JdbcTestUtils.countRowsInTable(this.jdbcTemplate, "gardener");
		signServiceImpl.signUp(signUpForm);
		assertEquals(previousCount + 1, JdbcTestUtils.countRowsInTable(this.jdbcTemplate, "gardener"));
	}

	/**
	 * 注册（用户名已存在）
	 */
	@Test(expected = ServiceException.class)
	@Transactional
	@Rollback
	@Sql(value = {
			"classpath:InsertGardener.sql"
	})
	public void testSignUp2() {
		signServiceImpl.signUp(signUpForm);
	}

	@After
	public void tearDown() {
		signUpForm = null;
	}

	@Resource
	SignService signServiceImpl;

	JdbcTemplate jdbcTemplate;

	SignUpForm signUpForm;

	@Autowired
	WebApplicationContext wac; // cached

	@Autowired
	MockServletContext servletContext; // cached

	@Autowired
	MockHttpSession session;

	@Autowired
	MockHttpServletRequest request;

	@Autowired
	MockHttpServletResponse response;

	@Autowired
	ServletWebRequest webRequest;

}

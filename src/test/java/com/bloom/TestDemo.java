package com.bloom;

import com.bloom.domain.gardener.SignService;
import com.bloom.exception.ServiceException;
import com.bloom.web.gardener.vo.SignUpForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * <p>Title: TestDemo.java<／p>
 * <p>Description: 几个单元测试的例子<／p>
 * <p>Copyright: Copyright (c) 2019<／p>
 * <p>Company: grasswort<／p>
 *
 * @author 树林里面丢了鞋
 * @version 1.0
 * @date 2019/1/27
 */
public class TestDemo extends SpringTextContext{
	@Resource
	private SignService signServiceImpl;
	@Autowired
	private WebApplicationContext wac;
	/**
	 * 注册表单数据
	 */
	private SignUpForm signUpForm;

	private JdbcTemplate jdbcTemplate;

	private MockMvc mockMvc;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * 单测示例 1: 注册（正常）
	 */
	@Test
	@Transactional
	public void testSiguUp1() {
		// 记录注册前 gardener 数量
		final int PREVIOUS_GARDENER_COUNT = JdbcTestUtils.countRowsInTable(this.jdbcTemplate, "gardener");
		// 注册
		signServiceImpl.signUp(signUpForm);
		// 判断 gardener 数量是否 + 1
		assertEquals(PREVIOUS_GARDENER_COUNT + 1,
				JdbcTestUtils.countRowsInTable(this.jdbcTemplate, "gardener"));
	}

	/**
	 * 单测示例 2: 注册（用户名已存在）
	 */
	@Test(expected = ServiceException.class)
	@Transactional
	@Sql(value = {
			"classpath:InsertGardener.sql"
	})
	public void testSignUp2() {
		signServiceImpl.signUp(signUpForm);
	}

	/**
	 * 单测示例 3: 测试 Controller
	 */
	@Test
	public void testControllerDemo() throws Exception{
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		this.mockMvc.perform(get("/gardeners/19")
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.id").value(19));
	}

	@Before
	public void setUp() {
		// 测试数据，String类型的请加上unit_前缀
		signUpForm = new SignUpForm();
		signUpForm.setNickName("unit_nickname");
		signUpForm.setUsername("unit_username");
		signUpForm.setPassword("unit_password");
		signUpForm.setEmail("835547206@qq.com");
	}

	@After
	public void tearDown() {
		signUpForm = null;
	}

}
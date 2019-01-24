package com.bloom;

import com.alibaba.fastjson.JSONObject;
import com.bloom.dao.GardenerMapper;
import com.bloom.dao.po.Gardener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextHierarchy({
		@ContextConfiguration(value = "classpath:spring-context-test.xml"),
		@ContextConfiguration(value = "file:src/main/webapp/WEB-INF/springmvc-servlet.xml")
})
@TestPropertySource(value = {
		"classpath:config/application-test.properties"
})
public class AppTest {
	@Autowired
	private HttpServletRequest request;

	@Resource
	private GardenerMapper gardenerMapper;

	@Test
	public void test() {
		assertNotNull(gardenerMapper);
		assertNotNull(request);
	}
}

package com.bloom;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * <p>Title: SpringTestContext.java<／p>
 * <p>Description: 基于Spring的测试环境<／p>
 * <p>Copyright: Copyright (c) 2019<／p>
 * <p>Company: grasswort<／p>
 *
 * @author 树林里面丢了鞋
 * @version 1.0
 * @date 2019/1/27
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextHierarchy({
		@ContextConfiguration(value = "classpath:spring-context-test.xml"),
		@ContextConfiguration(value = "file:src/main/webapp/WEB-INF/springmvc-servlet.xml")
})
@TestPropertySource(value = {
		"classpath:config/application-test.properties"
},properties = {
		"spring.test.context.cache.maxSize=32"
})
public class SpringTestContext {}
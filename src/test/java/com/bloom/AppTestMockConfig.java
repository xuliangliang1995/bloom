package com.bloom;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class AppTestMockConfig {

	@Bean
	public HttpServletRequest mockRequest() {
		return Mockito.mock(HttpServletRequest.class);
	}
}

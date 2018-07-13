package com.bloom.web.gardener.vo;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class SignInForm {
	@NotEmpty(message="{gardener.username.empty}")
	@Length(min=5,max=12,message="{gardener.username.length}")
	private String username;
	@NotEmpty(message="{gardener.password.empty}")
	@Length(min=8,max=20,message="{gardener.password.length}")
	private String password;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}

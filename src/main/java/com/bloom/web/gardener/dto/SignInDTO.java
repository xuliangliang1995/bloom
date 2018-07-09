package com.bloom.web.gardener.dto;

import javax.validation.Validation;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorPermission;
import org.hibernate.validator.constraints.Length;

public class SignInDTO {
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
	
	public static void main(String[] args) {
		Validation.byProvider(HibernateValidator.class)
		.configure()
		.failFast(true)
		.buildValidatorFactory();
	}

}

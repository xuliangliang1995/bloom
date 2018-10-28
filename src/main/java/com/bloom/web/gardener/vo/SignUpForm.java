package com.bloom.web.gardener.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class SignUpForm {
	@NotEmpty(message="{gardener.username.empty}")
	@Length(min=5,max=12,message="{gardener.username.length}")
	private String username;
	
	@NotEmpty(message="{gardener.password.empty}")
	@Length(min=8,max=20,message="{gardener.password.length}")
	private String password;
	
	@NotEmpty(message="{gardener.nickName.empty}")
	@Length(min=2,max=7,message="{gardener.nickName.length}")
	private String nickName;
	
	@NotEmpty(message="{gardener.email.empty}")
	@Email(message="{gardener.email.notValid}")
	private String email;
	
}

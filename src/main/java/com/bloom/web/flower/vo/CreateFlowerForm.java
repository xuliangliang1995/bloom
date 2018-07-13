package com.bloom.web.flower.vo;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

/**
 * 创建flower
 * @author 83554
 *
 */
public class CreateFlowerForm {
	@NotEmpty(message = "{flower.name.empty}")
	@Length(min = 2,max = 6,message = "{flower.name.length}")
	private String name;
	@NotEmpty(message = "{flower.moral.empty}")
	@Length(min = 5,max = 30,message = "{flower.moral.length}")
	private String moral;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMoral() {
		return moral;
	}
	public void setMoral(String moral) {
		this.moral = moral;
	}
	
}

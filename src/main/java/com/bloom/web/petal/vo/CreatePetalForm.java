package com.bloom.web.petal.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class CreatePetalForm {
	@Length(min = 2,max = 10,message = "{petal.name.length}")
	private String name;
	@Length(min = 5,max = 45,message = "{petal.note.length}")
	private String note;
	@NotNull(message = "{petal.variety.null}")
	private Integer petalVariety;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getPetalVariety() {
		return petalVariety;
	}

	public void setPetalVariety(Integer petalVariety) {
		this.petalVariety = petalVariety;
	}
	
	

}

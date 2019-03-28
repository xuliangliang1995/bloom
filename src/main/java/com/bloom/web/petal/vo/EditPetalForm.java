package com.bloom.web.petal.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EditPetalForm {
	@Length(min = 2,max = 10,message = "{petal.name.length}")
	private String name;
	@Length(min = 5,max = 45,message = "{petal.note.length}")
	private String note;
	@NotNull(message = "{petal.variety.null}")
	private Integer petalVariety;
	
	private String link;
	
	private String text;
	
	private String raw;

}

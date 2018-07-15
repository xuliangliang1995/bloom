package com.bloom.web.menu.vo;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class CreateMenuDTO {
	
	private Integer parentId;
	@NotEmpty(message = "{menu.name.empty}")
	@Length(min = 2, max = 5,message="{menu.name.length}")
	private String name;
	
	private String icon;
	
	private String target;

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	
}

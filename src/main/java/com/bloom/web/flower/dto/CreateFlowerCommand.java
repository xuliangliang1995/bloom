package com.bloom.web.flower.dto;
/**
 * 创建flower
 * @author 83554
 *
 */
public class CreateFlowerCommand {
	private String name;
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

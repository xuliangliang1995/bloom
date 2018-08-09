package com.bloom.domain.petal.meta;

public enum PetalVarietyEnum {
	RICH_TEXT(1,"富文本"),
	LINK(2,"链接");
	private int id;
	private String variety;
	
	private PetalVarietyEnum(int id, String variety) {
		this.id = id;
		this.variety = variety;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVariety() {
		return variety;
	}
	public void setVariety(String variety) {
		this.variety = variety;
	}
	

}

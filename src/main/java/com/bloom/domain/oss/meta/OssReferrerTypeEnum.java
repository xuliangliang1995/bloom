/**
 * 
 */
package com.bloom.domain.oss.meta;

/**
 * <p>Title: OssReferrerType.java<／p>
 * <p>Description: OSS引用者类型（在什么中引用了oss对象，例如petals）<／p>
 * <p>Copyright: Copyright (c) 2019<／p>
 * <p>Company: grasswort<／p>
 *
 * @author 树林里面丢了鞋
 * @date 2019年1月6日
 * @version 1.0
*/
public enum OssReferrerTypeEnum {
	AVATAR(1, "头像"),
	FLOWER(2, "花儿"),
	PETAL(3, "叶子");
	/**
	 * 引用者类型ID
	 */
	private int value;
	/**
	 * 引用者类型描述
	 */
	private String text;
	
	private OssReferrerTypeEnum(int value, String text) {
		this.value = value;
		this.text = text;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	

}

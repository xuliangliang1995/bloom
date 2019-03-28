package com.bloom.exception;

public class WechatException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String appId;
	private String openId;
	
	public WechatException(String appId, String openId, String message) {
		super(message);
		this.appId = appId;
		this.openId = openId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getMessage() {
		return this.getMessage();
	}
	

}

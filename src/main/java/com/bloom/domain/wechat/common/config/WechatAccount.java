package com.bloom.domain.wechat.common.config;

/**
 * 微信公众号配置
 * @author xuliangliang
 *
 */
public enum WechatAccount {
	GRASSWORT(
			"左思明月",
			"wxa9c6162f0ac68081",
			"8bf03cec95c0bde9ed9ba48f4cea3e6b",
			"C7WuKUHP6ImA3na4U95nRXQPjFOhb3lPfEnHD1pyqo4",
			"si_ming_yue"
			);
	private String name;
	private String appId;
	private String secret;
	private String aesKey;
	private String token;
	
	public String toString() {
		return name;
	}
	public String appId() {
		return appId;
	}
	public String secret() {
		return secret;
	}
	public String aesKey() {
		return aesKey;
	}
	public String token() {
		return token;
	}
	
	private WechatAccount(String name, String appId, String secret, String aesKey, String token) {
		this.name= name;
		this.appId = appId;
		this.secret = secret;
		this.aesKey = aesKey;
		this.token = token;
	}
	

}

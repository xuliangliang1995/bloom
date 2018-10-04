package com.bloom.domain.wechat.common.meta;

import com.bloom.domain.wechat.common.config.WechatAccount;

public enum TemplateMsg {
	ZUO_YE_TI_XING("3T-5TRP1uJDNE7WsJ-Id4cR8HZbJs-moq3PMRqXKux0",
				   "作业提醒",
				   "{{first.DATA}}\r\n" + 
				   "开始时间：{{keyword1.DATA}}\r\n" + 
				   "完成时间：{{keyword2.DATA}}\r\n" + 
				   "{{remark.DATA}}",
				   WechatAccount.GRASSWORT);
	private String id;
	private String title;
	private String template;
	private String support;
	private TemplateMsg(String id, String title, String template, WechatAccount account) {
		this.id = id;
		this.title = title;
		this.template = template;
		this.support = account.appId();
	}
	public boolean support(WechatAccount account) {
		return account.appId().equals(support);
	}
	public boolean support(String appId) {
		return appId.equals(support);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getSupport() {
		return support;
	}
	public void setSupport(String support) {
		this.support = support;
	}
	

}

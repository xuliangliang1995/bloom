package com.bloom.domain.wechat.common.consumer;

public abstract class AbstractConsumerBean {
	public AbstractConsumerBean(String key, WxMsgConsumer consumer) {
		this.key = key;
		this.consumer = consumer;
	}
	private String key;
	private WxMsgConsumer consumer;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public WxMsgConsumer getConsumer() {
		return consumer;
	}
	public void setConsumer(WxMsgConsumer consumer) {
		this.consumer = consumer;
	}
}

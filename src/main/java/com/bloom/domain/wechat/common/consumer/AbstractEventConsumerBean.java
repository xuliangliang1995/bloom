package com.bloom.domain.wechat.common.consumer;

/**
 * 继承此类且被Spring管理的将会自动被检测并搜集
 * @author xuliangliang
 *
 */
public abstract class AbstractEventConsumerBean {
	public AbstractEventConsumerBean(String key, WxMsgConsumer consumer) {
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

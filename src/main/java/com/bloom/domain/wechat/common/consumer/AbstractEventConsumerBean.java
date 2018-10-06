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
	public AbstractEventConsumerBean(String key) {
		this.key = key;
	}
	public abstract void consumerInit();
	private String key;
	protected WxMsgConsumer consumer;
	public String getKey() {
		return key;
	}
	public WxMsgConsumer getConsumer() {
		return consumer;
	}
	
	
	
}

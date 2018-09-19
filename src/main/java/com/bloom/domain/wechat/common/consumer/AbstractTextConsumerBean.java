package com.bloom.domain.wechat.common.consumer;

public abstract class AbstractTextConsumerBean {
	private int command;
	private WxMsgConsumer consumer;
	public AbstractTextConsumerBean(int command,WxMsgConsumer consumer) {
		super();
		this.command = command;
		this.consumer = consumer;
	}
	/**
	 * 可以匹配此Consumer所必须具备的前缀
	 * @param command
	 * @return
	 */
	public static final String prefix(int command) {
		return command + ":";
	}
	
	public int getCommand() {
		return command;
	}

	public void setCommand(int command) {
		this.command = command;
	}

	public WxMsgConsumer getConsumer() {
		return consumer;
	}

	public void setConsumer(WxMsgConsumer consumer) {
		this.consumer = consumer;
	}
	

}

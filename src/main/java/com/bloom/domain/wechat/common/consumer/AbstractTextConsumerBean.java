package com.bloom.domain.wechat.common.consumer;

public abstract class AbstractTextConsumerBean {
	private String command;
	private WxMsgConsumer consumer;
	public AbstractTextConsumerBean(String command,WxMsgConsumer consumer) {
		super();
		this.command = command;
		this.consumer = consumer;
	}
	/**
	 * 可以匹配此Consumer所必须具备的前缀
	 * @param command
	 * @return
	 */
	public static final String prefix(String command) {
		return command + ":";
	}
	
	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public WxMsgConsumer getConsumer() {
		return consumer;
	}

	public void setConsumer(WxMsgConsumer consumer) {
		this.consumer = consumer;
	}
	

}

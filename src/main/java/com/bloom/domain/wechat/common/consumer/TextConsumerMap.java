package com.bloom.domain.wechat.common.consumer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class TextConsumerMap implements ApplicationContextAware{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private ApplicationContext applicationContext;
	//根据事件Key映射消费者
	private static final Map<String,WxMsgConsumer> consumers = new HashMap<String,WxMsgConsumer>();
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	@PostConstruct
	public void gather(){
		Collection<AbstractTextConsumerBean> consumerBeans = applicationContext.getBeansOfType(AbstractTextConsumerBean.class).values();
		
		this.logger.info("\n搜集微信消息TextConsumer数量:{}",consumerBeans.size());
		
		consumerBeans.forEach(bean -> {
			consumers.put(bean.getCommand(), bean.getConsumer());
		});
	}
	
	/**
	 * 根据命令编号获取对应的处理器
	 * @param command
	 * @return
	 */
	public Optional<WxMsgConsumer> findConsumerByCommand(String command) {
		return Optional.ofNullable(consumers.get(command));
	}
	/**
	 * 注册文本消息处理器
	 */
	public void register(String command,WxMsgConsumer consumer) {
		if(consumers.containsKey(command)) {
			this.logger.info("\n微信文本消息处理Command:{}发生替换。",command);
		}
		consumers.put(command, consumer);
	}

}

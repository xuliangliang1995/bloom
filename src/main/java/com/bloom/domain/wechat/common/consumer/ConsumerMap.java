package com.bloom.domain.wechat.common.consumer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 微信消息消费Map
 * @author xuliangliang
 *
 */
@Component
public class ConsumerMap implements ApplicationContextAware{
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
		Collection<AbstractConsumerBean> consumerBeans = applicationContext.getBeansOfType(AbstractConsumerBean.class).values();
		
		this.logger.info("\n搜集微信消息Consumer数量:{}",consumerBeans.size());
		
		consumerBeans.forEach(bean -> {
			consumers.put(bean.getKey(), bean.getConsumer());
		});
	}
	
	/**
	 * 根据事件key获取消费者
	 * @param key
	 * @return
	 */
	public static Optional<WxMsgConsumer> findConsumer(String key) {
		return Optional.ofNullable(consumers.get(key));
	}

}

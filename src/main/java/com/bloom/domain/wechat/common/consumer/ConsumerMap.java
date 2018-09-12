package com.bloom.domain.wechat.common.consumer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
	private ApplicationContext applicationContext;
	//根据事件Key映射消费者
	private static final Map<String,WxMsgConsumer> consumers = new HashMap<String,WxMsgConsumer>();
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		Collection<AbstractConsumerBean> consumerBeans = this.applicationContext.getBeansOfType(AbstractConsumerBean.class).values();
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

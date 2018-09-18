package com.bloom.domain.wechat.common.consumer.bean.bind;

import org.springframework.stereotype.Component;

import com.bloom.domain.wechat.common.consumer.AbstractConsumerBean;

import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
@Component
public class BindGardener extends AbstractConsumerBean {
	public static final String KEY = "BIND_GARDENER";
	private static final String TEMPLATE = "BIND_GARDENER:%s#%s";
	
	public BindGardener() {
		super(KEY, ctx -> {
			WxMpXmlOutMessage wxMpXmlOutMessage = WxMpXmlOutMessage.TEXT()
					.content(
							String.format(TEMPLATE, "{账号}","{密码}")
							+"\n【例】:"
							+String.format(TEMPLATE, "grasswort","grasswort")
							)
					.fromUser(ctx.getWxMessage().getToUser())
					.toUser(ctx.getWxMessage().getFromUser())
					.build();
			ctx.setWxMpXmlOutMessage(wxMpXmlOutMessage);
		});
	}

}

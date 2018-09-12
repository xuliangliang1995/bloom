package com.bloom.domain.wechat.common.consumer.dto;

import java.util.Map;

import com.bloom.domain.wechat.common.consumer.WxMsgConsumer;

import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
@Getter
public class WxPostContext {
	private WxMpXmlMessage wxMessage;
	private Map<String, Object> context;
	private WxMpService wxMpService;
	private WxSessionManager sessionManager;
	@Setter
	private WxMpXmlOutMessage wxMpXmlOutMessage;
	
	public static WxPostContext pack(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) {
		WxPostContext ctx = new WxPostContext();
		ctx.wxMessage = wxMessage;
		ctx.context = context;
		ctx.wxMpService = wxMpService;
		ctx.sessionManager = sessionManager;
		return ctx;
	}
	
	public WxMpXmlOutMessage accept(WxMsgConsumer consumer) {
		consumer.accept(this);
		return this.wxMpXmlOutMessage;
	}
}

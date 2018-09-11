package com.bloom.domain.wechat.common.handler;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.bloom.domain.wechat.common.builder.TextBuilder;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

@Component
public class MsgHandler extends AbstractHandler {

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {

	    if (!wxMessage.getMsgType().equals(WxConsts.XmlMsgType.EVENT)) {
	      //TODO 可以选择将消息保存到本地
	    }

	    //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
	    if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
	      && wxMpService.getKefuService().kfOnlineList()
	      .getKfOnlineList().size()>0) {
	      return WxMpXmlOutMessage
	        .TRANSFER_CUSTOMER_SERVICE().fromUser(wxMessage.getToUser())
	        .toUser(wxMessage.getFromUser()).build();
	    }

	    //TODO 组装回复消息
	    String content = "倘若终究要痴，为花而痴，不也很美么。";
	    return new TextBuilder().build(content, wxMessage, wxMpService);

	  }

}

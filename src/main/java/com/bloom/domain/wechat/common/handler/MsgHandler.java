package com.bloom.domain.wechat.common.handler;

import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bloom.domain.wechat.common.builder.TextBuilder;
import com.bloom.domain.wechat.common.consumer.TextConsumerMap;
import com.bloom.domain.wechat.common.consumer.WxMsgConsumer;
import com.bloom.domain.wechat.common.consumer.dto.WxPostContext;
import com.bloom.util.general.NotNull;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

@Component
public class MsgHandler extends AbstractHandler {
	@Autowired
	private TextConsumerMap textConsumerMap;

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
	    
	    //判断是否是命令消息
	    String content = NotNull.of(wxMessage.getContent());
	    if(content.indexOf(":")>0) {
	    	String command = content.split(":")[0];
	    	
	    	this.logger.info("try to matching command : {}",command);
	    	if(NumberUtils.isDigits(command)) {
	    		Optional<WxMsgConsumer> consumerOpt = textConsumerMap.findConsumerByCommand(command);
	    		if(consumerOpt.isPresent()) {
	    			this.logger.info("matching success !");
	    			return WxPostContext.pack(wxMessage, context, wxMpService, sessionManager)
	    					.accept(consumerOpt.get());
	    		} else {
	    			this.logger.info("matching fail !");
	    		}
	    	}
	    }
	    
	    //TODO 组装回复消息
	    String text = "倘若终究要痴，为花而痴，不也很美么。";
	    return new TextBuilder().build(text, wxMessage, wxMpService);

	  }

}

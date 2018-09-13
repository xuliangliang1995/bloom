package com.bloom.domain.wechat.common.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.bloom.domain.wechat.common.builder.TextBuilder;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Component
public class SubscribeHandler extends AbstractHandler {

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {

	    this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());

	    // 获取微信用户基本信息
	    WxMpUser userWxInfo = wxMpService.getUserService().userInfo(wxMessage.getFromUser(), null);

	    if (userWxInfo != null) {
	      // TODO 可以添加关注用户到本地
	    }

	    WxMpXmlOutMessage responseResult = null;
	    try {
	      responseResult = handleSpecial(wxMessage);
	    } catch (Exception e) {
	      this.logger.error(e.getMessage(), e);
	    }

	    if (responseResult != null) {
	      return responseResult;
	    }

	    try {
	      return new TextBuilder().build("感谢关注", wxMessage, wxMpService);
	    } catch (Exception e) {
	      this.logger.error(e.getMessage(), e);
	    }

	    return null;
	  }
	
   /**
	* 处理特殊请求，比如如果是扫码进来的，可以做相应处理
	*/
	protected WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage) throws Exception {
	    //TODO
	    return null;
	}

}
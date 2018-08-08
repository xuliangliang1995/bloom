package com.bloom.web.wechat;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * 微信公众号卷耳
 * @author xuliangliang
 *
 */
@RestController
@RequestMapping("/wechat/grasswort")
public class GrasswortResourceApi {
	@Autowired
	@Qualifier("grasswort")
	private WxMpService grasswort;
	@Autowired
	private WxMpMessageRouter wxMpMessageRouter;
	
	
	@RequestMapping
	public void grasswortAuth(
		@RequestParam(value = "signature",required = false) String signature,
	    @RequestParam(value = "timestamp",required = false) String timestamp,
	    @RequestParam(value = "nonce", required = false) String nonce,
	    @RequestParam(value = "echostr", required = false) String echostr,
	    @RequestParam(value = "encrypt_type",required = false, defaultValue = "raw")String encryptType,
	    HttpServletRequest request,
	    HttpServletResponse response) throws IOException {
		if (!grasswort.checkSignature(timestamp, nonce, signature)) {
	        // 消息签名不正确，说明不是公众平台发过来的消息
	        response.getWriter().println("非法请求");
	        return;
	    }
		
		if (StringUtils.isNotBlank(echostr)) {
	        // 说明是一个仅仅用来验证的请求，回显echostr
	        response.getWriter().println(echostr);
	        return;
	    }

		if ("raw".equals(encryptType)) {
		     // 明文传输的消息
		     WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
		     WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
		     response.getWriter().write(outMessage.toXml());
		     return;
		}

	    if ("aes".equals(encryptType)) {
	      // 是aes加密的消息
	      String msgSignature = request.getParameter("msg_signature");
	      WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(), grasswort.getWxMpConfigStorage(), timestamp, nonce, msgSignature);
	      WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
	      response.getWriter().write(outMessage.toEncryptedXml(grasswort.getWxMpConfigStorage()));
	      return;
	    }

		response.getWriter().println("不可识别的加密类型");
		return;
		
	}

}

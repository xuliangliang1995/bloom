package com.bloom.domain.wechat.common.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloom.domain.wechat.common.router.RouterGenerator;
import com.bloom.domain.wechat.common.service.WxMpPortalService;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
@Service
public class WxMpPortalServiceImpl implements WxMpPortalService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RouterGenerator routerGenerator;
	
	@Override
	public String authGet(WxMpService wxMpService,String signature, String timestamp, String nonce, String echostr) {
		this.logger.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature, timestamp, nonce, echostr);

	    if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
	      throw new IllegalArgumentException("请求参数非法，请核实!");
	    }

	    if (wxMpService.checkSignature(timestamp, nonce, signature)) {
	      return echostr;
	    }

	    return "非法请求";
	  }

	@Override
	public String process(WxMpService wxMpService,String requestBody, String signature, String encType, String msgSignature, String timestamp,
			String nonce) {
	    this.logger.info("\n接收微信请求：[signature=[{}], encType=[{}], msgSignature=[{}],"
	            + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
	          signature, encType, msgSignature, timestamp, nonce, requestBody);
	    	
	        if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
	          throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
	        }

	        String out = null;
	        if (encType == null) {
	          // 明文传输的消息
	          WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
	          WxMpXmlOutMessage outMessage = routerGenerator.generateRouter(wxMpService).route(inMessage);
	          if (outMessage == null) {
	            return "";
	          }

	          out = outMessage.toXml();
	        } else if ("aes".equals(encType)) {
	          // aes加密的消息
	          WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(requestBody,
	        		  wxMpService.getWxMpConfigStorage(), timestamp, nonce, msgSignature);
	          this.logger.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
	          WxMpXmlOutMessage outMessage = routerGenerator.generateRouter(wxMpService).route(inMessage);
	          if (outMessage == null) {
	            return "";
	          }

	          out = outMessage.toEncryptedXml(wxMpService.getWxMpConfigStorage());
	        }

	        this.logger.debug("\n组装回复信息：{}", out);

	        return out;
	      }

}

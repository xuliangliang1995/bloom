package com.bloom.domain.wechat.common.service;

import me.chanjar.weixin.mp.api.WxMpService;

public interface WxMpPortalService {
	
	String authGet(WxMpService wxMpService,String signature,String timestamp,String nonce,String echostr);
	
	String process(WxMpService wxMpService,String requestBody,String signature,String encType,String msgSignature,String timestamp,String nonce);

}

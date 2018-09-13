package com.bloom.web.wechat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bloom.domain.wechat.common.service.WxMpPortalService;

import me.chanjar.weixin.mp.api.WxMpService;

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
	private WxMpPortalService wxMpPortalServiceImpl;
	
	@GetMapping(produces = "text/plain;charset=utf-8")
	public String authGet(@RequestParam(name = "signature", required = false) String signature,
	                      @RequestParam(name = "timestamp", required = false) String timestamp,
	                      @RequestParam(name = "nonce", required = false) String nonce,
	                      @RequestParam(name = "echostr", required = false) String echostr) {
		return wxMpPortalServiceImpl.authGet(grasswort, signature, timestamp, nonce, echostr);
	}
	
	@PostMapping(produces = "application/xml; charset=UTF-8")
	public String progress(
			@RequestBody String requestBody, 
			@RequestParam("signature") String signature,
            @RequestParam(name = "encrypt_type", required = false) String encType,
            @RequestParam(name = "msg_signature", required = false) String msgSignature,
            @RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce) {
		return wxMpPortalServiceImpl.process(grasswort, requestBody, signature, encType, msgSignature, timestamp, nonce);
	}
}

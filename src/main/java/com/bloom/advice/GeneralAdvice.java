package com.bloom.advice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bloom.domain.wechat.common.router.WxMpServiceGenerator;
import com.bloom.exception.ServiceException;
import com.bloom.exception.WechatException;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

@ControllerAdvice
public class GeneralAdvice {
	@Autowired
	private WxMpServiceGenerator wxMpServiceGenerator;
	
	@ResponseBody
	@ExceptionHandler(ServiceException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public VndErrors flowBreakException(ServiceException ex) {
		return new VndErrors("error", ex.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(WechatException.class)
	@ResponseStatus(HttpStatus.OK)
	public String wechatException(WechatException ex) {
		WxMpXmlOutMessage out = WxMpXmlOutMessage.TEXT()
				.content(ex.getMessage())
				.toUser(ex.getOpenId())
				.build();
		Optional<WxMpService> wxMpServiceOpt = wxMpServiceGenerator.get(ex.getAppId());
		if (wxMpServiceOpt.isPresent()) {
			return out.toEncryptedXml(wxMpServiceOpt.get().getWxMpConfigStorage());
		}
		return "";
	}
	
}

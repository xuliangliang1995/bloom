package com.bloom.domain.wechat.common.consumer.bean.test;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Component;

import com.bloom.domain.wechat.common.consumer.AbstractEventConsumerBean;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
@Component
public class TemplateMsgTest extends AbstractEventConsumerBean {
	public static final String KEY = "TEMPLATE_MSG_TEST";
	public TemplateMsgTest() {
		super(KEY, ctx -> {
			WxMpTemplateMessage message = WxMpTemplateMessage
					.builder()
					.templateId("dX0jh5QtZBCK-yz0YJFhVxWHuFTARCdCKRq5lfbXMac")
					.toUser(ctx.getWxMessage().getFromUser())
					.url("https://github.com/xuliangliang1995")
					.build()
					.addData(new WxMpTemplateData("first", "这是一条模板消息。"))
					.addData(new WxMpTemplateData("keyword1", "Github"))
					.addData(new WxMpTemplateData("keyword2", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm")))
					.addData(new WxMpTemplateData("remark", "点击可以查看该项目Github仓库。"));
			
			WxMpService wxMpService = ctx.getWxMpService();
			try {
				wxMpService.getTemplateMsgService().sendTemplateMsg(message);
			} catch (WxErrorException e) {
				e.printStackTrace();
			}
		});
	}
	@Override
	public void consumerInit() {
		//ignore，已经在构造参数中初始化
	}

}

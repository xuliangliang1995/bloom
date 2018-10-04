package com.bloom.domain.wechat.common.consumer.bean.test;

import org.springframework.stereotype.Component;

import com.bloom.domain.wechat.common.consumer.AbstractEventConsumerBean;
import com.bloom.util.image.RandomImage;

import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage.Item;
@Component
public class KeFuMsgNewsTest extends AbstractEventConsumerBean {
	public static final String KEY = "KEFU_MSG_NEWS_TEST";
	
	public KeFuMsgNewsTest() {
		super(KEY, ctx -> {
			Item article = new Item();
			article.setTitle("蜻蜓与狮");
			article.setPicUrl(RandomImage.get());
			article.setDescription("我慢慢的溶化在泥土里,翅膀上仿若有你的鼻息...");
			article.setUrl("https://music.163.com/#/song?id=29792835&market=baiduqk");
			
			WxMpXmlOutMessage wxMpXmlOutMessage = WxMpXmlOutMessage.NEWS()
					.addArticle(article)
					.fromUser(ctx.getWxMessage().getToUser())
					.toUser(ctx.getWxMessage().getFromUser())
					.build();
			ctx.setWxMpXmlOutMessage(wxMpXmlOutMessage);
		});
	}

}

package com.bloom.domain.wechat.common.consumer.bean;

import org.springframework.stereotype.Component;

import com.bloom.domain.wechat.common.consumer.AbstractConsumerBean;

import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage.Item;
@Component
public class KeFuMsgNewsTest extends AbstractConsumerBean {
	public static final String KEY = "KEFU_MSG_NEWS_TEST";
	
	public KeFuMsgNewsTest() {
		super(KEY, ctx -> {
			Item article = new Item();
			article.setTitle("蜻蜓与狮");
			article.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536778670264&di=07c7563309915c3a239345a3820d6f3b&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01cc13554242c80000019ae9e173e9.jpg%401280w_1l_2o_100sh.jpg");
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

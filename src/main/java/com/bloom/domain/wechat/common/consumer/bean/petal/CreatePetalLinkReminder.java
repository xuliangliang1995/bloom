package com.bloom.domain.wechat.common.consumer.bean.petal;

import static com.bloom.domain.wechat.common.consumer.AbstractTextConsumerBean.prefix;

import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import com.bloom.domain.wechat.common.constant.UsingCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bloom.dao.po.Flower;
import com.bloom.dao.po.Gardener;
import com.bloom.dao.po.Petal;
import com.bloom.domain.flower.FlowerService;
import com.bloom.domain.gardener.GardenerWechatOpenIdService;
import com.bloom.domain.gardener.SignService;
import com.bloom.domain.petal.PetalService;
import com.bloom.domain.petal.meta.PetalVarietyEnum;
import com.bloom.domain.wechat.common.consumer.AbstractEventConsumerBean;
import com.bloom.domain.wechat.common.consumer.TextConsumerMap;
import com.bloom.domain.wechat.common.consumer.WxMsgConsumer;
import com.bloom.exception.ServiceException;
import com.bloom.util.image.RandomImage;
import com.bloom.web.petal.vo.CreatePetalForm;

import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage.Item;

@Component
public class CreatePetalLinkReminder extends AbstractEventConsumerBean{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//绑定账号提醒Event KEY
	public static final String KEY = "SAVE_PETAL_LINK";
	//绑定账号消息模版
	private static final String TEMPLATE = UsingCommand.COMMAND_002.concat(":%s#%s#%s");
	//命令编号
	public static final String COMMAND = UsingCommand.COMMAND_002;
	
	@Autowired
	private FlowerService flowerServiceImpl;
	@Autowired
	private PetalService petalServiceImpl;
	@Autowired
	private TextConsumerMap textConsumerMap;
	@Autowired
	private SignService signServiceImpl;
	@Autowired
	private HttpServletRequest request;
	
	public CreatePetalLinkReminder() {
		super(KEY, ctx -> {
			WxMpXmlOutMessage wxMpXmlOutMessage = WxMpXmlOutMessage.TEXT()
					.content(String.format(TEMPLATE, "{标题}","{链接}","{备注}"))
					.fromUser(ctx.getWxMessage().getToUser())
					.toUser(ctx.getWxMessage().getFromUser())
					.build();
			ctx.setWxMpXmlOutMessage(wxMpXmlOutMessage);
		});
	}
	
	@PostConstruct
	public void register() {
		WxMsgConsumer consumer = ctx -> {
			String content = ctx.getWxMessage().getContent();
			String openId = ctx.getWxMessage().getFromUser();
			String appId = ctx.getWxMpService().getWxMpConfigStorage().getAppId();
			
			String outMessageContent = "";
			//确认命令
			if (content.startsWith(prefix(COMMAND))) {
				String [] params = content.replaceFirst(prefix(COMMAND), "").split("#");
				if (params.length == 3) {
					String name = params[0];
					String link = params[1];
					String note = params[2];
					
					Gardener gardener = signServiceImpl.signInByWechatOpenId(request, appId, openId);
					
					//find default flower 
					Flower defaultFlower = flowerServiceImpl.defaultFlower(gardener.getId());
					//封装叶片数据
					CreatePetalForm form = new CreatePetalForm();
					form.setName(name);
					form.setLink(link);
					form.setNote(note);
					form.setPetalVariety(PetalVarietyEnum.LINK.getId());
					//添加叶片
					Petal petal = petalServiceImpl.add(defaultFlower, form);
					
					Item article = new Item();
					article.setTitle(petal.getName());
					article.setPicUrl(RandomImage.get());
					article.setDescription(petal.getNote());
					article.setUrl(petalServiceImpl.getPetalInnerLinkService().findByPetalId(petal.getId()).getLink());
					
					WxMpXmlOutMessage wxMpXmlOutMessage = WxMpXmlOutMessage.NEWS()
							.addArticle(article)
							.fromUser(ctx.getWxMessage().getToUser())
							.toUser(ctx.getWxMessage().getFromUser())
							.build();
					ctx.setWxMpXmlOutMessage(wxMpXmlOutMessage);
				} 
				if (null == ctx.getWxMpXmlOutMessage()) {
					//绑定成功后,消息反馈
					WxMpXmlOutMessage outMessage = WxMpXmlOutMessage.TEXT()
							.content(outMessageContent)
							.fromUser(ctx.getWxMessage().getToUser())
							.toUser(openId)
							.build();
					ctx.setWxMpXmlOutMessage(outMessage);
				}
				
			} else {
				throw new ServiceException("command is unmatched");
			}
			
		};
		textConsumerMap.register(COMMAND, consumer);
	}

	@Override
	public void consumerInit() {
		// ignore
	}
}

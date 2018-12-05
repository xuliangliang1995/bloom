package com.bloom.domain.wechat.common.consumer.bean.petal;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bloom.dao.po.Gardener;
import com.bloom.dao.po.Petal;
import com.bloom.domain.gardener.SignService;
import com.bloom.domain.petal.PetalProgressService;
import com.bloom.domain.petal.PetalService;
import com.bloom.domain.petal.meta.PetalVarietyEnum;
import com.bloom.domain.wechat.common.consumer.AbstractEventConsumerBean;
import com.bloom.util.image.RandomImage;
import com.bloom.web.petal.PetalResourceApi;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage.WxArticle;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class TodayFiredPetal extends AbstractEventConsumerBean {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PetalProgressService petalProgressServiceImpl;
	@Autowired
	private SignService signServiceImpl;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private PetalService petalServiceImpl;
	
	public static final String KEY = "TODAY_FIRED_PETAL";

	public TodayFiredPetal() {
		super(KEY);
	}

	@Override
	@PostConstruct
	public void consumerInit() {
		this.consumer = ctx -> {
			String appId = ctx.getWxMpService().getWxMpConfigStorage().getAppId();
			String openId = ctx.getWxMessage().getFromUser();
			Gardener gardener = signServiceImpl.signInByWechatOpenId(request, appId, openId);
			
			List<Integer> petalIds = petalProgressServiceImpl.todayFiredAndNoFiredPetalList(gardener.getId());
			
			if(petalIds.isEmpty()) {
				WxMpXmlOutMessage out = WxMpXmlOutMessage.TEXT()
						.content("今日没有要复习的内容！")
						.toUser(openId)
						.build();
				ctx.setWxMpXmlOutMessage(out);
				return;
			}
			WxMpKefuMessage out = WxMpKefuMessage.NEWS()
					.toUser(openId)
					.build();
			for (Integer petalId : petalIds) {
				
				Petal petal = petalServiceImpl.findByPetalId(petalId);
				WxArticle article = new WxArticle();
				article.setTitle(petal.getName());
				article.setPicUrl(RandomImage.get());
				article.setDescription(petal.getNote());
				
				PetalVarietyEnum variety = Arrays.stream(PetalVarietyEnum.values())
						.filter(v -> v.getId() == petal.getPetalVarietyId())
						.findFirst().get();
				switch (variety) {
				case LINK:
					article.setUrl(petalServiceImpl.getPetalInnerLinkService().findByPetalId(petal.getId()).getLink());
					break;

				case RICH_TEXT:
					article.setUrl(linkTo(methodOn(PetalResourceApi.class).petalPage(petal.getFlowerId(), petal.getId())).withSelfRel().getHref());
					break;
				}
				
				
				out.getArticles().add(article);
				
				if(out.getArticles().size()==1) {
					try {
						ctx.getWxMpService().getKefuService().sendKefuMessage(out);
					} catch (WxErrorException e) {
						e.printStackTrace();
					} finally {
						out = WxMpKefuMessage.NEWS()
								.toUser(openId)
								.build();
					}
				}
			}
			try {
				ctx.getWxMpService().getKefuService().sendKefuMessage(out);
			} catch (WxErrorException e) {
				e.printStackTrace();
			}
		};
	}

}

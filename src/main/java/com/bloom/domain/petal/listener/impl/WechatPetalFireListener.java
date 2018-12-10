package com.bloom.domain.petal.listener.impl;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bloom.dao.po.GardenerWechatOpenId;
import com.bloom.dao.po.Petal;
import com.bloom.domain.gardener.GardenerWechatOpenIdService;
import com.bloom.domain.petal.PetalProgressService;
import com.bloom.domain.petal.PetalService;
import com.bloom.domain.petal.listener.PetalFireEvent;
import com.bloom.domain.petal.listener.PetalFireListener;
import com.bloom.domain.petal.listener.PetalFireSource;
import com.bloom.domain.petal.meta.PetalVarietyEnum;
import com.bloom.domain.wechat.common.meta.TemplateMsg;
import com.bloom.domain.wechat.common.router.WxMpServiceGenerator;
import com.bloom.web.petal.PetalResourceApi;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

@Component
public class WechatPetalFireListener implements PetalFireListener {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PetalFireSource petalFireSource;
	@Autowired
	private PetalService petalServiceImpl;
	@Autowired
	private GardenerWechatOpenIdService gardenerWechatOpenIdServiceImpl;
	@Autowired
	private WxMpServiceGenerator wxMpServiceGenerator;
	@Autowired
	private PetalProgressService petalProgressServiceImpl;
	

	@Override
	@PostConstruct
	public void listen() {
		petalFireSource.register(this);
	}

	@Override
	public void notListen() {
		petalFireSource.delete(this);
	}

	@Override
	public void accept(PetalFireEvent fireEvent) {
		Petal petal = fireEvent.getPetal();
		
		String url = "";
		PetalVarietyEnum variety = Arrays.stream(PetalVarietyEnum.values()).filter(v -> petal.getPetalVarietyId().equals(v.getId()))
				.findFirst().get();
		switch (variety) {
		case LINK:
			url = petalServiceImpl.getPetalInnerLinkService().findByPetalId(petal.getId()).getLink();
			break;
		case RICH_TEXT:
			url = linkTo(methodOn(PetalResourceApi.class).petalPage(petal.getFlowerId(), petal.getId())).withSelfRel().getHref()
				.replaceAll("http://","https://grasswort.com/");
			break;
		}
		
		WxMpTemplateMessage tmsg = WxMpTemplateMessage.builder()
				.templateId(TemplateMsg.ZUO_YE_TI_XING.getId())
				.data(Arrays.asList(
						new WxMpTemplateData("first","您有新的内容需要复习啦 ~ "),
						new WxMpTemplateData("keyword1",DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm")),
						new WxMpTemplateData("keyword2",String.format("【%s】", petal.getName()))  ,
						new WxMpTemplateData("remark","你可以选择不复习，但努力会让你更出色噢~")
						))
				.url(url)
				.build();
		List<GardenerWechatOpenId> openIdList = gardenerWechatOpenIdServiceImpl.getBindWechatOpenIdByGardenerId(petal.getGardenerId());
		
		openIdList.parallelStream().forEach(item -> {
			// 首先，模版消息要支持该微信公众号
			if (TemplateMsg.ZUO_YE_TI_XING.support(item.getAppId())) {
				// 存在该公众号的配置信息
				Optional<WxMpService> wxMpService = wxMpServiceGenerator.get(item.getAppId());
				
				if (wxMpService.isPresent()) {
					try {
						tmsg.setToUser(item.getOpenId());
						wxMpService.get().getTemplateMsgService().sendTemplateMsg(tmsg);
					} catch (WxErrorException e) {
						logger.error("\n微信模板消息【{}】发送失败:{}",TemplateMsg.ZUO_YE_TI_XING.getTitle(),e.getMessage());
						e.printStackTrace();
					}
				}
				
			} else {
				// ignore
			}
		});
	
		petalProgressServiceImpl.createNextProgress(petal);
	}

}

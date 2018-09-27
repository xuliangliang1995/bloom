package com.bloom.domain.petal.listener.impl;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

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
import com.bloom.domain.wechat.common.router.WxMpServiceGenerator;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage.WxArticle;

@Component
public class WechatPetalFireListener implements PetalFireListener {
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
		
		WxArticle article = new WxArticle();
		article.setTitle(petal.getName());
		article.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536778670264&di=07c7563309915c3a239345a3820d6f3b&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01cc13554242c80000019ae9e173e9.jpg%401280w_1l_2o_100sh.jpg");
		article.setDescription(petal.getNote());
		article.setUrl(petalServiceImpl.getPetalInnerLinkService().findByPetalId(petal.getId()).getLink());
		
		List<GardenerWechatOpenId> openIdList = gardenerWechatOpenIdServiceImpl.getBindWechatOpenIdByGardenerId(petal.getGardenerId());
		
		openIdList.parallelStream().forEach(item -> {
			WxMpKefuMessage kefuMessage = WxMpKefuMessage.NEWS()
					.addArticle(article)
					.toUser(item.getOpenId())
					.build();
			
			Optional<WxMpService> wxMpService = wxMpServiceGenerator.get(item.getAppId());
			
			if (wxMpService.isPresent()) {
				try {
					wxMpService.get().getKefuService().sendKefuMessage(kefuMessage);
				} catch (WxErrorException e) {
					e.printStackTrace();
				}
			}
			
		});
		
		petalProgressServiceImpl.createNextProgress(petal);
	}

}

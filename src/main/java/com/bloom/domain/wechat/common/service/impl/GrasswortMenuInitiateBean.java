package com.bloom.domain.wechat.common.service.impl;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.bloom.domain.wechat.common.consumer.bean.KeFuMsgNewsTest;
import com.bloom.domain.wechat.common.consumer.bean.TemplateMsgTest;
import com.bloom.domain.wechat.common.service.WxMpMenuInitService;

import me.chanjar.weixin.common.api.WxConsts.MenuButtonType;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;

@Component
public class GrasswortMenuInitiateBean implements WxMpMenuInitService{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	@Qualifier("grasswort")
	private WxMpService grasswort;
	
	@Override
	@PostConstruct
	public void init() {
		WxMenu menu = new WxMenu();
		
		WxMenuButton button1 = new WxMenuButton();
		button1.setType(MenuButtonType.VIEW);
		button1.setName("搜索");
		button1.setUrl("http://www.baidu.com/");
		menu.getButtons().add(button1);
		
		WxMenuButton button2 = new WxMenuButton();
		button2.setType(MenuButtonType.VIEW);
		button2.setName("视频");
		button2.setUrl("http://v.qq.com/");
		menu.getButtons().add(button2);
		
		WxMenuButton button3 = new WxMenuButton();
		button3.setName("功能");
		menu.getButtons().add(button3);
		
			WxMenuButton button31 = new WxMenuButton();
			button31.setType(MenuButtonType.CLICK);
			button31.setName("图文消息");
			button31.setKey(KeFuMsgNewsTest.KEY);
			
			WxMenuButton button32 = new WxMenuButton();
			button32.setType(MenuButtonType.CLICK);
			button32.setName("模板消息");
			button32.setKey(TemplateMsgTest.KEY);
			
			WxMenuButton button33 = new WxMenuButton();
			button33.setType(MenuButtonType.VIEW);
			button33.setName("源码仓库");
			button33.setUrl("https://github.com/xuliangliang1995");
		
		button3.getSubButtons().add(button31);
		button3.getSubButtons().add(button32);	
		button3.getSubButtons().add(button33);
		
		try {
			String result = grasswort.getMenuService().menuCreate(menu);
			this.logger.info("\n微信公众号【{}】菜单初始化成功！\n{}","grasswort",result);
		} catch (WxErrorException e) {
			this.logger.error("\n微信公众号【{}】菜单初始化失败！","grasswort");
			e.printStackTrace();
		}
	}

}

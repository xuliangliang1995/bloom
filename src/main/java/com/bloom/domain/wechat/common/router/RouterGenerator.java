package com.bloom.domain.wechat.common.router;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloom.domain.wechat.common.handler.KfSessionHandler;
import com.bloom.domain.wechat.common.handler.LocationHandler;
import com.bloom.domain.wechat.common.handler.LogHandler;
import com.bloom.domain.wechat.common.handler.MenuHandler;
import com.bloom.domain.wechat.common.handler.MsgHandler;
import com.bloom.domain.wechat.common.handler.NullHandler;
import com.bloom.domain.wechat.common.handler.StoreCheckNotifyHandler;
import com.bloom.domain.wechat.common.handler.SubscribeHandler;
import com.bloom.domain.wechat.common.handler.UnsubscribeHandler;

import me.chanjar.weixin.common.api.WxConsts.EventType;
import me.chanjar.weixin.common.api.WxConsts.MenuButtonType;
import me.chanjar.weixin.common.api.WxConsts.XmlMsgType;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.constant.WxMpEventConstants;

/**
 * 微信公众号路由生成器
 * @author xuliangliang
 *
 */
@Service
public class RouterGenerator {
	@Autowired
	private KfSessionHandler kfSessionHandler;
	@Autowired
	private LocationHandler locationHandler;
	@Autowired
	private LogHandler logHandler;
	@Autowired
	private MenuHandler menuHandler;
	@Autowired
	private MsgHandler msgHandler;
	@Autowired
	private NullHandler nullHandler;
	@Autowired
	private StoreCheckNotifyHandler storeCheckNotifyHandler;
	@Autowired
	private SubscribeHandler subscribeHandler;
	@Autowired
	private UnsubscribeHandler unsubscribeHandler;
	
	private static final Map<String,WxMpMessageRouter> routers = new HashMap<String,WxMpMessageRouter>();
	
	/**
	 * 
	 * @param wxMpService
	 * @return WxMpMessageRouter
	 */
	public WxMpMessageRouter generateRouter(WxMpService wxMpService) {
		String appId = wxMpService.getWxMpConfigStorage().getAppId();
		if(!routers.containsKey(appId)) {
			synchronized (this) {
				final WxMpMessageRouter newRouter = new WxMpMessageRouter(wxMpService);
				// 记录所有事件的日志
			    newRouter.rule().handler(this.logHandler).next();

			    // 接收客服会话管理事件
			    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
			      .event(WxMpEventConstants.CustomerService.KF_CREATE_SESSION)
			      .handler(this.kfSessionHandler).end();
			    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
			      .event(WxMpEventConstants.CustomerService.KF_CLOSE_SESSION)
			      .handler(this.kfSessionHandler).end();
			    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
			      .event(WxMpEventConstants.CustomerService.KF_SWITCH_SESSION)
			      .handler(this.kfSessionHandler).end();

			    // 门店审核事件
			    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
			      .event(WxMpEventConstants.POI_CHECK_NOTIFY)
			      .handler(this.storeCheckNotifyHandler)
			      .end();

			    // 自定义菜单事件
			    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
			      .event(MenuButtonType.CLICK).handler(this.menuHandler).end();

			    // 点击菜单连接事件
			    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
			      .event(MenuButtonType.VIEW).handler(this.nullHandler).end();

			    // 关注事件
			    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
			      .event(EventType.SUBSCRIBE).handler(this.subscribeHandler)
			      .end();

			    // 取消关注事件
			    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
			      .event(EventType.UNSUBSCRIBE).handler(this.unsubscribeHandler)
			      .end();

			    // 上报地理位置事件
			    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
			      .event(EventType.LOCATION).handler(this.locationHandler).end();

			    // 接收地理位置消息
			    newRouter.rule().async(false).msgType(XmlMsgType.LOCATION)
			      .handler(this.locationHandler).end();

			    // 扫码事件
			    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
			      .event(EventType.SCAN).handler(this.nullHandler).end();

			    // 默认
			    newRouter.rule().async(false).handler(this.msgHandler).end();
			    routers.put(appId, newRouter);
			}
		}
		return routers.get(appId);
	}
}

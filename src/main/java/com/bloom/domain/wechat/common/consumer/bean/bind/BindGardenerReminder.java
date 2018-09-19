package com.bloom.domain.wechat.common.consumer.bean.bind;

import static com.bloom.domain.wechat.common.consumer.AbstractTextConsumerBean.prefix;

import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bloom.dao.ext.GardenerExtDao;
import com.bloom.dao.po.Gardener;
import com.bloom.domain.gardener.GardenerWechatOpenIdService;
import com.bloom.domain.gardener.SignService;
import com.bloom.domain.wechat.common.consumer.AbstractEventConsumerBean;
import com.bloom.domain.wechat.common.consumer.TextConsumerMap;
import com.bloom.domain.wechat.common.consumer.WxMsgConsumer;
import com.bloom.exception.FlowBreakException;
import com.bloom.util.encrypt.GardenerEncrypt;

import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
@Component
public class BindGardenerReminder extends AbstractEventConsumerBean {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//绑定账号提醒Event KEY
	public static final String KEY = "BIND_GARDENER";
	//绑定账号消息模版
	private static final String TEMPLATE = "001:%s#%s";
	//命令编号
	public static final int COMMAND = 001;
	
	@Autowired
	private GardenerWechatOpenIdService gardenerWechatOpenIdServiceImpl;
	@Autowired
	private TextConsumerMap textConsumerMap;
	@Resource
	private GardenerExtDao gardenerExtDao;
	
	public BindGardenerReminder() {
		super(KEY, ctx -> {
			WxMpXmlOutMessage wxMpXmlOutMessage = WxMpXmlOutMessage.TEXT()
					.content(String.format(TEMPLATE, "{账号}","{密码}"))
					.fromUser(ctx.getWxMessage().getToUser())
					.toUser(ctx.getWxMessage().getFromUser())
					.build();
			ctx.setWxMpXmlOutMessage(wxMpXmlOutMessage);
		});
	}
	
	/**
	 * Gardener绑定微信公众号openId功能注册
	 */
	@PostConstruct
	public void register() {
		WxMsgConsumer consumer = ctx -> {
			String content = ctx.getWxMessage().getContent();
			String openId = ctx.getWxMessage().getFromUser();
			
			String outMessageContent = "";
			//确认命令
			if (content.startsWith(prefix(COMMAND))) {
				String [] params = content.replaceFirst(prefix(COMMAND), "").split("#");
				if(params.length == 2) {
					String originalUsername = params[0];
					String originalPassword = params[1];
					String appId = ctx.getWxMpService().getWxMpConfigStorage().getAppId();
					Optional<Integer> keyOpt = Optional.ofNullable(
							gardenerExtDao.selectKeyByUsername(GardenerEncrypt.encryptUsername(originalUsername))
							);
					if(keyOpt.isPresent()) {
						String password = GardenerEncrypt.encryptPassword(keyOpt.get(), originalUsername, originalPassword);
						Gardener gardener = gardenerExtDao.selectByPrimaryKey(keyOpt.get());
						if (password.equals(gardener.getPassword())) {
							//账户密码正确,进行绑定
							gardenerWechatOpenIdServiceImpl.bindWechatOpenId(gardener.getId(), appId, openId);
							outMessageContent = "绑定成功！";
						} else {
							//密码有误
							outMessageContent = "密码有误！";
						}
						
					} else {
						//账户不存在
						outMessageContent = "账户不存在！请先前往注册。https://www.grasswort.com";
					}
				}
				//绑定成功后,消息反馈
				WxMpXmlOutMessage outMessage = WxMpXmlOutMessage.TEXT()
						.content(outMessageContent)
						.fromUser(ctx.getWxMessage().getToUser())
						.toUser(openId)
						.build();
				ctx.setWxMpXmlOutMessage(outMessage);
				
			} else {
				throw new FlowBreakException("command is unmatched");
			}
			
		};
		//注册处理器
		textConsumerMap.register(COMMAND, consumer);
		
	}

}

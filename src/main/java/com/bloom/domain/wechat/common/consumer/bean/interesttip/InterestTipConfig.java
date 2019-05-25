package com.bloom.domain.wechat.common.consumer.bean.interesttip;

import com.alibaba.fastjson.JSONObject;
import com.bloom.domain.wechat.common.constant.UsingCommand;
import com.bloom.domain.wechat.common.consumer.AbstractEventConsumerBean;
import com.bloom.domain.wechat.common.consumer.TextConsumerMap;
import com.bloom.domain.wechat.common.consumer.WxMsgConsumer;
import com.bloom.exception.ServiceException;
import com.bloom.util.okhttp.OkHttp;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static com.bloom.domain.wechat.common.consumer.AbstractTextConsumerBean.prefix;

@Component
public class InterestTipConfig extends AbstractEventConsumerBean {

    @Autowired
    private TextConsumerMap textConsumerMap;

    public static final String KEY = "INTEREST_TIP";

    public static final String TEMPLATE = UsingCommand.COMMAND_003.concat(":%s#%s");

    public static final String COMMAND = UsingCommand.COMMAND_003;

    public InterestTipConfig() {
        super(KEY, ctx -> {
            WxMpXmlOutMessage wxMpXmlOutMessage = WxMpXmlOutMessage.TEXT()
                    .content(String.format(TEMPLATE, "{提醒编号}","{提醒内容}"))
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
            /*String openId = ctx.getWxMessage().getFromUser();
            String appId = ctx.getWxMpService().getWxMpConfigStorage().getAppId();*/

            //确认命令
            if (content.startsWith(prefix(COMMAND))) {
                String [] params = content.replaceFirst(prefix(COMMAND), "").split("#");
                if (params.length == 2) {
                    String sceneIdStr = params[0];
                    String tip = params[1];

                    JSONObject json = new JSONObject();
                    json.put("scene_id", sceneIdStr);
                    json.put("tip", tip);

                    OkHttp.post("http://123.207.163.197:4009/interest/tip", json.toJSONString());
                    //OkHttp.post("http://www.xiaolaohr.com/interest/tip", json.toJSONString());

                    WxMpXmlOutMessage wxMpXmlOutMessage = WxMpXmlOutMessage.TEXT()
                            .content("成功发送修改请求。")
                            .fromUser(ctx.getWxMessage().getToUser())
                            .toUser(ctx.getWxMessage().getFromUser())
                            .build();
                    ctx.setWxMpXmlOutMessage(wxMpXmlOutMessage);
                }
                if (null == ctx.getWxMpXmlOutMessage()) {
                    //绑定成功后,消息反馈
                    WxMpXmlOutMessage wxMpXmlOutMessage = WxMpXmlOutMessage.TEXT()
                            .content("设置失败")
                            .fromUser(ctx.getWxMessage().getToUser())
                            .toUser(ctx.getWxMessage().getFromUser())
                            .build();
                    ctx.setWxMpXmlOutMessage(wxMpXmlOutMessage);
                }

            } else {
                throw new ServiceException("command is unmatched");
            }

        };
        textConsumerMap.register(COMMAND, consumer);
    }

    @Override
    public void consumerInit() {

    }
}

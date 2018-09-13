package com.bloom.domain.wechat.common.consumer;

import java.util.function.Consumer;

import com.bloom.domain.wechat.common.consumer.dto.WxPostContext;

public interface WxMsgConsumer extends Consumer<WxPostContext> {

}

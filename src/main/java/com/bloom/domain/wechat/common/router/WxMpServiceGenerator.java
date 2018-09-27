package com.bloom.domain.wechat.common.router;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.bloom.domain.wechat.common.config.WechatAccount;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

/**
 * 微信公众号服务生成器
 * @author xuliangliang
 *
 */
@Service
public class WxMpServiceGenerator {
	
	private final HashMap<String,WxMpService> wxServiceMap = new HashMap<String,WxMpService>();
	
	@PostConstruct
	public void init() {
		Arrays.stream(WechatAccount.values()).forEach(account -> {
			String key = account.appId();
			//配置
			WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
			config.setAppId(account.appId());
			config.setSecret(account.secret());
			config.setAesKey(account.aesKey());
			config.setToken(account.token());
			
			WxMpService service = new WxMpServiceImpl();
			service.setWxMpConfigStorage(config);
			
			wxServiceMap.put(key, service);
		});
	}
	
	public Optional<WxMpService> get(String appId) {
		return Optional.ofNullable(wxServiceMap.get(appId));
	}
	
	public Optional<WxMpService> get(WechatAccount account){
		return Optional.ofNullable(wxServiceMap.get(account.appId()));
	}

}

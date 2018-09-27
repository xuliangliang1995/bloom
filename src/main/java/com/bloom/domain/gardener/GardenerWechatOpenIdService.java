package com.bloom.domain.gardener;

import java.util.List;
import java.util.Optional;

import com.bloom.dao.po.GardenerWechatOpenId;
/**
 * Gardener和微信公众号OpenId绑定
 * @author xuliangliang
 *
 */
public interface GardenerWechatOpenIdService {
	
	void bindWechatOpenId(int gardenerId,String appId,String openId);
	
	Optional<Integer> getGardenerIdByWechatOpenId(String appId,String openId);
	
	List<GardenerWechatOpenId> getBindWechatOpenIdByGardenerId(int gardenerId);

}

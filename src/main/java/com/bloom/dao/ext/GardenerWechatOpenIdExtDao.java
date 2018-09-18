package com.bloom.dao.ext;

import org.apache.ibatis.annotations.Param;

import com.bloom.dao.GardenerWechatOpenIdMapper;

public interface GardenerWechatOpenIdExtDao extends GardenerWechatOpenIdMapper {
	
	void deleteByGardenerId(@Param("gardenerId")int gardenerId,@Param("appId")String appId);

}

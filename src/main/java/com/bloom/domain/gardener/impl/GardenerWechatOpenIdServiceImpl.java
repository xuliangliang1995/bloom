package com.bloom.domain.gardener.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloom.dao.ext.GardenerWechatOpenIdExtDao;
import com.bloom.dao.po.GardenerWechatOpenId;
import com.bloom.dao.po.GardenerWechatOpenIdExample;
import com.bloom.domain.gardener.GardenerWechatOpenIdService;

@Service
public class GardenerWechatOpenIdServiceImpl implements GardenerWechatOpenIdService {
	@Autowired
	private GardenerWechatOpenIdExtDao gardenerWechatOpenIdExtDao;
	
	@Override
	@Transactional
	public void bindWechatOpenId(int gardenerId, String appId, String openId) {
		//解除之前的绑定
		gardenerWechatOpenIdExtDao.deleteByGardenerId(gardenerId,appId);
		
		//创建新的绑定
		GardenerWechatOpenId bindInfo = new GardenerWechatOpenId();
		bindInfo.setGardenerId(gardenerId);
		bindInfo.setAppId(appId);
		bindInfo.setOpenId(openId);
		bindInfo.setCt(new Date());
		
		gardenerWechatOpenIdExtDao.insert(bindInfo);
	}

	@Override
	public Optional<Integer> getGardenerIdByWechatOpenId(String appId, String openId) {
		
		GardenerWechatOpenIdExample example = new GardenerWechatOpenIdExample();
		example.createCriteria()
			   .andAppIdEqualTo(appId)
			   .andOpenIdEqualTo(openId);
		
		return gardenerWechatOpenIdExtDao.selectByExample(example).stream()
				.findFirst().map(GardenerWechatOpenId::getGardenerId);
	}

}

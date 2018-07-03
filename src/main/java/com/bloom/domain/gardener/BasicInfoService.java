package com.bloom.domain.gardener;

import java.util.Date;

import com.bloom.dao.po.Gardener;
import com.bloom.domain.gardener.meta.Gender;

/**
 * 基本信息
 * @author 83554
 *
 */
public interface BasicInfoService {
	
	Gardener basicInfo(Integer gardenerKey,String nickName,Gender gender,Date birthday);

	Gardener setEmail(Integer gardenerKey,String email);
	
	Gardener findGardenerById(int id);
}

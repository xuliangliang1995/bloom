package com.bloom.domain.gardener;

import java.util.Date;

import com.bloom.domain.gardener.meta.Gender;

/**
 * 基本信息
 * @author 83554
 *
 */
public interface BasicInfoService {
	
	void basicInfo(Integer gardenerKey,String nickName,Gender gender,Date birthday);

	void setEmail(Integer gardenerKey,String email);
}

package com.bloom.domain.gardener.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloom.dao.ext.GardenerExtDao;
import com.bloom.dao.po.Gardener;
import com.bloom.domain.gardener.BasicInfoService;
import com.bloom.domain.gardener.meta.Gender;
@Service
public class BasicInfoServiceImpl implements BasicInfoService {
	@Resource
	private GardenerExtDao gardenerExtDao;

	@Override
	@Transactional
	public void basicInfo(Integer gardenerKey, String nickName, Gender gender, Date birthday) {
		Date now = new Date();
		Gardener gardener = new Gardener();
		gardener.setId(gardenerKey);
		gardener.setNickName(nickName);
		gardener.setGender(gender.name());
		gardener.setBirthday(birthday);
		gardener.setUt(now);
		gardenerExtDao.updateByPrimaryKeySelective(gardener);
	}

	@Override
	@Transactional
	public void setEmail(Integer gardenerKey, String email) {
		Date now = new Date();
		Gardener gardener = new Gardener();
		gardener.setId(gardenerKey);
		gardener.setEmail(email);
		gardener.setUt(now);
		gardenerExtDao.updateByPrimaryKeySelective(gardener);
	}

}

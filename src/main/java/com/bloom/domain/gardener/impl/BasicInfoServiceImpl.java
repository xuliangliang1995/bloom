package com.bloom.domain.gardener.impl;

import com.bloom.dao.ext.GardenerExtDao;
import com.bloom.dao.po.Gardener;
import com.bloom.domain.CachedName;
import com.bloom.domain.gardener.BasicInfoService;
import com.bloom.domain.gardener.meta.Gender;
import com.bloom.exception.ServiceException;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;
@Service
public class BasicInfoServiceImpl implements BasicInfoService {
	@Resource
	private GardenerExtDao gardenerExtDao;

	@Override
	@Transactional
	@CachePut(cacheNames = CachedName.GARDENERS, key = "#result.id")
	public Gardener basicInfo(Integer gardenerKey, String nickName, Gender gender, Date birthday) {
		Date now = new Date();
		Gardener gardener = new Gardener();
		gardener.setId(gardenerKey);
		gardener.setNickName(nickName);
		gardener.setGender(gender.name());
		gardener.setBirthday(birthday);
		gardener.setUt(now);
		gardenerExtDao.updateByPrimaryKeySelective(gardener);
		return gardener;
	}

	@Override
	@Transactional
	@CachePut(cacheNames = CachedName.GARDENERS, key = "#result.id")
	public Gardener setEmail(Integer gardenerKey, String email) {
		Date now = new Date();
		Gardener gardener = new Gardener();
		gardener.setId(gardenerKey);
		gardener.setEmail(email);
		gardener.setUt(now);
		gardenerExtDao.updateByPrimaryKeySelective(gardener);
		return gardener;
	}

	@Override
	@Cacheable(cacheNames = CachedName.GARDENERS, key = "#id")
	public Gardener findGardenerById(int id) {
		return Optional.ofNullable(
				gardenerExtDao.selectByPrimaryKey(id)
				)
				.orElseThrow(() -> new ServiceException("该用户不存在！"));
	}
	

}

package com.bloom.domain.petal.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bloom.dao.ext.PetalVarietyExtDao;
import com.bloom.dao.po.PetalVariety;
import com.bloom.dao.po.PetalVarietyExample;
import com.bloom.domain.CachedName;
import com.bloom.domain.petal.PetalVarietyService;
/**
 * 叶子种类
 * @author 83554
 *
 */
@Service
public class PetalVarietyServiceImpl implements PetalVarietyService {
	@Autowired
	private PetalVarietyExtDao petalVarietyExtDao;

	@Override
	@Cacheable(cacheNames = CachedName.petal, key = "#root.methodName")
	public List<PetalVariety> varieties() {
		PetalVarietyExample query = new PetalVarietyExample();
		return petalVarietyExtDao.selectByExample(query);
	}

	@Override
	@Cacheable(cacheNames = CachedName.petal, key = "#varietyId")
	public Optional<PetalVariety> findById(int varietyId) {
		return Optional.ofNullable(petalVarietyExtDao.selectByPrimaryKey(varietyId));
	}

}

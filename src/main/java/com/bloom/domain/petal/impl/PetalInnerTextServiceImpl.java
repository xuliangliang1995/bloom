package com.bloom.domain.petal.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloom.dao.ext.PetalInnerTextExtDao;
import com.bloom.dao.po.Petal;
import com.bloom.dao.po.PetalInnerText;
import com.bloom.dao.po.PetalInnerTextWithBLOBs;
import com.bloom.domain.CachedName;
import com.bloom.domain.petal.PetalInnerTextService;
import com.bloom.exception.FlowBreakException;
@Service
public class PetalInnerTextServiceImpl implements PetalInnerTextService {
	@Autowired
	private PetalInnerTextExtDao petalInnerTextExtDao;

	@Override
	@CachePut(cacheNames = CachedName.PETAL_TEXT, key = "#petal.id")
	public PetalInnerTextWithBLOBs addPetalText(Petal petal, String text, String raw) {
		Date now = new Date();
		PetalInnerTextWithBLOBs innerText = new PetalInnerTextWithBLOBs();
		innerText.setPetalId(petal.getId());
		innerText.setText(text);
		innerText.setRaw(raw);
		innerText.setCt(now);
		innerText.setUt(now);
		petalInnerTextExtDao.insert(innerText);
		return innerText;
	}

	@Override
	@Cacheable(cacheNames = CachedName.PETAL_TEXT, key = "#petalId")
	public PetalInnerTextWithBLOBs findByPetalId(int petalId) {
		return Optional.ofNullable(
				petalInnerTextExtDao.findByPetalId(petalId)
				).orElseThrow(() -> new FlowBreakException("资源不存在或已被删除！"));
	}

	@Override
	@CacheEvict(cacheNames = CachedName.PETAL_TEXT, key = "#petalId")
	@Transactional
	public void deletePetalInnerText(int petalId) {
		petalInnerTextExtDao.deleteByPetalId(petalId);
	}
}

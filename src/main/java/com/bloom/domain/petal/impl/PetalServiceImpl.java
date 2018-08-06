package com.bloom.domain.petal.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloom.dao.ext.PetalExtDao;
import com.bloom.dao.po.Flower;
import com.bloom.dao.po.Petal;
import com.bloom.dao.po.PetalExample;
import com.bloom.domain.CachedName;
import com.bloom.domain.gardener.general.LoginCheckUtil;
import com.bloom.domain.petal.PetalProgressService;
import com.bloom.domain.petal.PetalService;
import com.bloom.exception.FlowBreakException;
import com.bloom.web.petal.vo.CreatePetalForm;
/**
 * petal
 * @author 83554
 *
 */
@Service
public class PetalServiceImpl implements PetalService {
	@Autowired
	private PetalExtDao petalExtDao;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private PetalProgressService petalProgressServiceImpl;
	
	@Override
	@Cacheable(cacheNames = CachedName.petal, key = "#petalId")
	public Petal findByPetalId(int petalId) {
		return Optional.ofNullable(petalExtDao.selectByPrimaryKey(petalId))
				.orElseThrow(() -> new FlowBreakException("资源不存在或已被删除！"));
	}

	@Override
	@Transactional
	@CachePut(cacheNames = CachedName.petal, key = "#result.id")
	public Petal add(Flower flower,CreatePetalForm createPetalForm) {
		Date now = new Date();
		int gardenerId = LoginCheckUtil.loginGardenerId(request);
		Petal petal = new Petal();
		petal.setGardenerId(gardenerId);
		petal.setFlowerId(flower.getId());
		petal.setName(createPetalForm.getName());
		petal.setNote(createPetalForm.getNote());
		petal.setPetalVarietyId(createPetalForm.getPetalVariety());
		petal.setCt(now);
		petal.setUt(now);
		petalExtDao.insert(petal);
		petalProgressServiceImpl.initProgress(petal);
		return petal;
	}

	@Override
	public List<Petal> flowerPetals(int flowerId) {
		PetalExample query = new PetalExample();
		query.createCriteria().andFlowerIdEqualTo(flowerId);
		query.setOrderByClause("id desc");
		return petalExtDao.selectByExample(query);
	}

}

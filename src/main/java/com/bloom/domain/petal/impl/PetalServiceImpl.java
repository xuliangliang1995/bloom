package com.bloom.domain.petal.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.bloom.dao.ext.PetalExtDao;
import com.bloom.dao.po.Flower;
import com.bloom.dao.po.Petal;
import com.bloom.dao.po.PetalExample;
import com.bloom.domain.CachedName;
import com.bloom.domain.gardener.general.LoginCheckUtil;
import com.bloom.domain.petal.PetalInnerLinkService;
import com.bloom.domain.petal.PetalInnerTextService;
import com.bloom.domain.petal.PetalProgressService;
import com.bloom.domain.petal.PetalService;
import com.bloom.domain.petal.meta.PetalVarietyEnum;
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
	private HttpServletRequest request;
	@Autowired
	private PetalExtDao petalExtDao;
	@Autowired
	private PetalProgressService petalProgressServiceImpl;
	@Autowired
	private PetalInnerLinkService petalInnerLinkServiceImpl;
	@Autowired
	private PetalInnerTextService petalInnerTextServiceImpl;
	
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
		Assert.isTrue(LoginCheckUtil.loginGardenerId(request)==flower.getGardenerId(),"权限不足！");
		PetalVarietyEnum variety = Arrays.stream(PetalVarietyEnum.values())
				.filter(vari -> vari.getId() == createPetalForm.getPetalVariety().intValue())
				.findFirst()
				.orElseThrow(() -> new FlowBreakException("叶子种类有误！"));
		
		Date now = new Date();
		Petal petal = new Petal();
		petal.setGardenerId(flower.getGardenerId());
		petal.setFlowerId(flower.getId());
		petal.setName(createPetalForm.getName());
		petal.setNote(createPetalForm.getNote());
		petal.setPetalVarietyId(createPetalForm.getPetalVariety());
		petal.setCt(now);
		petal.setUt(now);
		petalExtDao.insert(petal);
		
		switch (variety) {
		case LINK:
			petalInnerLinkServiceImpl.addPetalLink(petal, createPetalForm.getLink());
			break;
		case RICH_TEXT:
			petalInnerTextServiceImpl.addPetalText(petal, createPetalForm.getText());
			break;
		}
		
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

	@Override
	public PetalInnerLinkService getPetalInnerLinkService() {
		return this.petalInnerLinkServiceImpl;
	}

	@Override
	public PetalInnerTextService getPetalInnerTextService() {
		return this.petalInnerTextServiceImpl;
	}

}

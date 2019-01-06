package com.bloom.domain.petal.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.bloom.dao.ext.PetalExtDao;
import com.bloom.dao.po.Flower;
import com.bloom.dao.po.Petal;
import com.bloom.domain.CachedName;
import com.bloom.domain.gardener.general.LoginCheckUtil;
import com.bloom.domain.petal.PetalInnerLinkService;
import com.bloom.domain.petal.PetalInnerTextService;
import com.bloom.domain.petal.PetalProgressService;
import com.bloom.domain.petal.PetalService;
import com.bloom.domain.petal.meta.PetalVarietyEnum;
import com.bloom.exception.FlowBreakException;
import com.bloom.util.mybatis.Page;
import com.bloom.web.petal.vo.CreatePetalForm;
import com.bloom.web.petal.vo.EditPetalForm;
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
	@Cacheable(cacheNames = CachedName.PETAL, key = "#petalId")
	public Petal findByPetalId(int petalId) {
		return Optional.ofNullable(petalExtDao.selectByPrimaryKey(petalId))
				.orElseThrow(() -> new FlowBreakException("资源不存在或已被删除！"));
	}

	@Override
	@Transactional
	@CachePut(cacheNames = CachedName.PETAL, key = "#result.id")
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
			petalInnerTextServiceImpl.addPetalText(petal, createPetalForm.getText(), createPetalForm.getRaw());
			break;
		}
		
		petalProgressServiceImpl.initProgress(petal);
		return petal;
	}

	/**
	 * 编辑叶子
	 * @param flower
	 * @param editPetalForm
	 * @return
	 */
	@Transactional
	@CachePut(cacheNames = CachedName.PETAL, key = "#result.id")
	public Petal edit(int petalId,Flower flower,EditPetalForm editPetalForm) {
		Assert.isTrue(LoginCheckUtil.loginGardenerId(request)==flower.getGardenerId(),"权限不足！");
		PetalVarietyEnum variety = Arrays.stream(PetalVarietyEnum.values())
				.filter(vari -> vari.getId() == editPetalForm.getPetalVariety().intValue())
				.findFirst()
				.orElseThrow(() -> new FlowBreakException("叶子种类有误！"));
		
		Date now = new Date();
		Petal petal = Optional.ofNullable(
				petalExtDao.selectByPrimaryKey(petalId)
				)
				.filter(p -> p.getFlowerId().equals(flower.getId()))
				.orElseThrow(() -> new FlowBreakException("操作对象不存在或已被删除！"));
		
		Assert.isTrue(petal.getPetalVarietyId().equals(variety.getId()),"暂不支持更改类型");
		
		petal.setGardenerId(flower.getGardenerId());
		petal.setFlowerId(flower.getId());
		petal.setName(editPetalForm.getName());
		petal.setNote(editPetalForm.getNote());
		petal.setPetalVarietyId(editPetalForm.getPetalVariety());
		petal.setUt(now);
		petalExtDao.updateByPrimaryKey(petal);
		
		switch (variety) {
		case LINK:
			petalInnerLinkServiceImpl.deletePetalInnerLink(petal.getId());
			petalInnerLinkServiceImpl.addPetalLink(petal, editPetalForm.getLink());
			break;
		case RICH_TEXT:
			petalInnerTextServiceImpl.editPetalText(petal, editPetalForm.getText(), editPetalForm.getRaw());
			break;
		}
		return petal;
	}
	
	@Override
	@Transactional
	@CacheEvict(cacheNames = CachedName.PETAL, key = "#petalId")
	public void deletePetal(int petalId,Flower flower) {
		Assert.isTrue(LoginCheckUtil.loginGardenerId(request)==flower.getGardenerId(),"权限不足！");
		
		Petal petal = Optional.ofNullable(
				petalExtDao.selectByPrimaryKey(petalId)
				)
				.filter(p -> p.getFlowerId().equals(flower.getId()))
				.orElseThrow(() -> new FlowBreakException("操作对象不存在或已被删除！"));
		
		PetalVarietyEnum variety = Arrays.stream(PetalVarietyEnum.values())
				.filter(v -> petal.getPetalVarietyId().equals(v.getId()))
				.findFirst().get();
		
		//删除叶子
		petalExtDao.deleteByPrimaryKey(petalId);
		//删除叶子对应的进程
		petalProgressServiceImpl.deletePetalProgressByPetalId(petalId);
		//删除叶片内容
		switch (variety) {
		case LINK:
			petalInnerLinkServiceImpl.deletePetalInnerLink(petal.getId());
			break;

		case RICH_TEXT:
			petalInnerTextServiceImpl.deletePetalInnerText(petal.getId());
			break;
		}
		
		
	}
	
	@Override
	public List<Petal> flowerPetals(int flowerId,Page<?> page) {
		return petalExtDao.flowerPetals(flowerId, page);
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

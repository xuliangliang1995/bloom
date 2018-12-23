package com.bloom.domain.flower.impl;
import java.util.Date;
/**
 * 花朵er
 */
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.bloom.dao.ext.FlowerExtDao;
import com.bloom.dao.po.Flower;
import com.bloom.dao.po.FlowerExample;
import com.bloom.domain.CachedName;
import com.bloom.domain.flower.FlowerService;
import com.bloom.domain.flower.meta.FlowerStar;
import com.bloom.domain.gardener.general.LoginCheckUtil;
import com.bloom.domain.petal.PetalService;
import com.bloom.exception.ServiceException;
import com.bloom.util.mybatis.Page;
import com.bloom.web.flower.vo.CreateFlowerForm;
import com.bloom.web.flower.vo.EditFlowerForm;
@Service
public class FlowerServiceImpl implements FlowerService {
	@Resource
	private FlowerExtDao flowerExtDao;
	@Autowired
	private HttpServletRequest request;
	@Resource
	private PetalService petalServiceImpl;
	
	private static final String DEFAULT_FLOWER_NAME = "grasswort";

	@Override
	@Transactional
	@CachePut(cacheNames = CachedName.FLOWER, key = "#result.id")
	public Flower create(int gardenerId, CreateFlowerForm form) {
		Assert.isTrue(LoginCheckUtil.loginGardenerId(request)==gardenerId,"权限不足！");
		Date now = new Date();
		Flower flower = new Flower();
		flower.setGardenerId(gardenerId);
		flower.setName(form.getName());
		flower.setMoral(form.getMoral());
		flower.setStar(FlowerStar.Star_1.value());
		flower.setCt(now);
		flower.setUt(now);
		flowerExtDao.insert(flower);
		return flower;
	}
	
	@Override
	@Transactional
	@CachePut(cacheNames = CachedName.FLOWER, key = "#result.id")
	public Flower defaultFlower(int gardenerId) {
		Assert.isTrue(LoginCheckUtil.loginGardenerId(request)==gardenerId,"权限不足！");
		FlowerExample example = new FlowerExample();
		example.createCriteria().andNameEqualTo(DEFAULT_FLOWER_NAME)
								.andGardenerIdEqualTo(gardenerId);
		Flower defaultFlower = flowerExtDao.selectByExample(example).stream().findFirst().orElse(null);
		
		if (null == defaultFlower) {
			//不存在、创建
			Date now = new Date();
			Flower flower = new Flower();
			flower.setGardenerId(gardenerId);
			flower.setName(DEFAULT_FLOWER_NAME);
			flower.setMoral("未指定花儿的叶片将默认归属于此。");
			flower.setStar(FlowerStar.Star_1.value());
			flower.setCt(now);
			flower.setUt(now);
			flowerExtDao.insert(flower);
			return flower;
		} else {
			//存在、返回
			return defaultFlower;
		}
	}

	@Override
	@Transactional
	@CacheEvict(cacheNames = CachedName.FLOWER, key = "#flowerId")
	public void deleteById(int gardenerId, int flowerId) {
		Assert.isTrue(gardenerId == LoginCheckUtil.loginGardenerId(request),"操作权限不足！");
		Flower flower = Optional.ofNullable(
				flowerExtDao.selectByPrimaryKey(flowerId)
				)
				.filter(sflower -> sflower.getGardenerId().equals(gardenerId))
				.orElseThrow(() -> new ServiceException("您要操作的资源不存在或已被删除！"));
		//先删除所有该flower下的petals
		petalServiceImpl.flowerPetals(flowerId, null).forEach(petal -> petalServiceImpl.deletePetal(petal.getId(), flower));
		
		flowerExtDao.deleteByPrimaryKey(flower.getId());
	}

	@Override
	@Transactional
	@CachePut(cacheNames = CachedName.FLOWER, key = "#result.id")
	public Flower edit(int gardenerId,int flowerId,EditFlowerForm editFlowerForm) {
		Assert.isTrue(gardenerId == LoginCheckUtil.loginGardenerId(request),"操作权限不足！");
		
		Flower flower = Optional.ofNullable(
				flowerExtDao.selectByPrimaryKey(flowerId)
				)
				.filter(sflower -> sflower.getGardenerId().equals(gardenerId))
				.orElseThrow(() -> new ServiceException("您要编辑的资源不存在或已被删除！"));
		flower.setName(editFlowerForm.getName());
		flower.setMoral(editFlowerForm.getMoral());
		flower.setUt(new Date());
		
		flowerExtDao.updateByPrimaryKey(flower);
		return flower;
	}

	@Override
	@Cacheable(cacheNames = CachedName.FLOWER, key = "#id")
	public Flower findById(int id) {
		return Optional.ofNullable(
				flowerExtDao.selectByPrimaryKey(id)
				).orElseThrow(() -> new ServiceException("资源不存在或已被删除！"));
	}

	@Override
	public List<Flower> findFlowerByGardener(int gardenerId,Page<?> page) {
		return flowerExtDao.findFlowersByGardenerId(gardenerId, page);
	}

}

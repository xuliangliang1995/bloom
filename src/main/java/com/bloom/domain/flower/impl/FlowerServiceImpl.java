package com.bloom.domain.flower.impl;
import java.util.Date;
/**
 * 花朵er
 */
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;
import org.testng.Assert;

import com.bloom.dao.FlowerMapper;
import com.bloom.dao.po.Flower;
import com.bloom.domain.flower.FlowerService;
import com.bloom.domain.flower.meta.FlowerStar;
import com.bloom.domain.gardener.meta.SessionConstantKey;
import com.bloom.exception.FlowBreakException;
@Service
public class FlowerServiceImpl implements FlowerService {
	@Resource
	private FlowerMapper flowerMapper;

	@Override
	public Flower create(HttpServletRequest request, Flower flower) {
		Date now = new Date();
		Integer gardenerId = Optional.ofNullable(
				(Integer)WebUtils.getSessionAttribute(request, SessionConstantKey.GARDENER_ENTIRY_KEY)
				)
				.orElseThrow(() -> new FlowBreakException("您还没有登录！"));
		Assert.assertTrue(gardenerId.equals(flower.getId()),"您没有该操作权限！");
		Assert.assertTrue(StringUtils.hasText(flower.getName()),"花儿不能没有名字！");
		flower.setStar(FlowerStar.Star_1.value());
		flower.getName();
		flower.setCt(now);
		flower.setUt(now);
		flowerMapper.insert(flower);
		return flower;
	}

	@Override
	public void deleteById(HttpServletRequest request, Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit(HttpServletRequest request, Flower flower) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Flower> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Flower> findFlowerByGardener(Integer gardenerId) {
		// TODO Auto-generated method stub
		return null;
	}

}

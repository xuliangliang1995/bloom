package com.bloom.domain.petal.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloom.dao.ext.PetalProgressExtDao;
import com.bloom.dao.po.Petal;
import com.bloom.dao.po.PetalProgress;
import com.bloom.dao.po.PetalProgressExample;
import com.bloom.dao.po.RetentionCurve;
import com.bloom.domain.petal.PetalProgressService;
import com.bloom.domain.petal.PetalService;
import com.bloom.domain.petal.factory.PetalProgressFactory;
import com.bloom.domain.petal.listener.PetalFireSource;
import com.bloom.domain.petal.meta.PetalProgressFireStatus;
import com.bloom.domain.retentioncurve.RetentionCurveService;
import com.bloom.util.time.GeneralDateUtils;
@Service
public class PetalProgressServiceImpl implements PetalProgressService {
	@Autowired
	private RetentionCurveService retentionCurveServiceImpl;
	@Autowired
	private PetalProgressExtDao petalProgressExtDao;
	@Autowired
	private PetalFireSource petalFireSource;
	@Autowired
	private PetalService petalServiceImpl;
	
	/**
	 * 初始化第一个进度（确定入参petal为刚创建的petal）
	 */
	@Override
	@Transactional
	@Async
	public void initProgress(Petal petal) {
		List<RetentionCurve> curves = retentionCurveServiceImpl.enabledRetentionCurves();
		PetalProgress progress = PetalProgressFactory.create(petal, curves.get(0));
		petalProgressExtDao.insert(progress);
	}

	@Override
	@Transactional
	public PetalProgress createNextProgress(Petal petal) {
		PetalProgress progress = petalProgressExtDao.currentProgress(petal.getId());
		List<RetentionCurve> curves = retentionCurveServiceImpl.enabledRetentionCurves();
		System.out.println(curves.size());
		RetentionCurve nextCurve = curves.stream()
				.filter(curve -> curve.getId()>progress.getRetentionCurveId()).findFirst()
				.orElse(curves.get(curves.size()-1));
		PetalProgress nextProgress = PetalProgressFactory.create(petal, nextCurve);
		petalProgressExtDao.insert(nextProgress);
		return nextProgress;
	}

	@Override
	@Transactional
	public void fire(long progressId) {
		PetalProgress progress = petalProgressExtDao.selectByPrimaryKey(progressId);
		progress.setFire(PetalProgressFireStatus.FIRE.status());
		petalProgressExtDao.updateByPrimaryKey(progress);
		
		Petal petal = petalServiceImpl.findByPetalId(progress.getPetalId());
		petalFireSource.fire(petal);
	}
	
	@Override
	public List<Long> outdatedNoFireProgerssIdList(){
		Date now = new Date();
		PetalProgressExample example = new PetalProgressExample();
		example.createCriteria()
			   .andFireEqualTo(PetalProgressFireStatus.NO_FIRE.status())
			   .andFireTimeLessThanOrEqualTo(now);
		return petalProgressExtDao.selectByExample(example)
				.stream()
				.map(PetalProgress::getId)
				.collect(Collectors.toList());
	}
	
	@Override
	public List<Integer> todayFiredPetalList(int gardenerId){
		PetalProgressExample example = new PetalProgressExample();
		
		example.createCriteria()
			   .andGardenerIdEqualTo(gardenerId)
			   .andFireTimeGreaterThan(GeneralDateUtils.todayStart())
			   .andFireEqualTo(PetalProgressFireStatus.FIRE.status());
		
		return petalProgressExtDao.selectByExample(example)
				                  .stream()
				                  .map(PetalProgress::getPetalId)
				                  .distinct()
				                  .collect(Collectors.toList());
	}
	
	@Override
	public List<Integer> todayFiredAndNoFiredPetalList(int gardenerId){
		PetalProgressExample example = new PetalProgressExample();
		
		example.createCriteria()
			   .andGardenerIdEqualTo(gardenerId)
			   .andFireTimeBetween(GeneralDateUtils.todayStart(), GeneralDateUtils.todayEnd());
		
		return petalProgressExtDao.selectByExample(example)
				                  .stream()
				                  .map(PetalProgress::getPetalId)
				                  .distinct()
				                  .collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public void deletePetalProgressByPetalId(int petalId) {
		petalProgressExtDao.deletePetalProgress(petalId);
	}

	@Override
	public List<PetalProgress> petalProgress(int gardenerId,Date startDate, Date endDate) {
		PetalProgressExample example = new PetalProgressExample();
		example.createCriteria()
			.andGardenerIdEqualTo(gardenerId)
			.andFireTimeBetween(startDate, endDate);
		example.setOrderByClause("fire_time desc,id desc");
		return petalProgressExtDao.selectByExample(example)
				;
	}
}

package com.bloom.domain.petal.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloom.dao.ext.PetalProgressExtDao;
import com.bloom.dao.po.Petal;
import com.bloom.dao.po.PetalProgress;
import com.bloom.dao.po.RetentionCurve;
import com.bloom.domain.petal.PetalProgressService;
import com.bloom.domain.petal.factory.PetalProgressFactory;
import com.bloom.domain.retentioncurve.RetentionCurveService;
@Service
public class PetalProgressServiceImpl implements PetalProgressService {
	@Autowired
	private RetentionCurveService retentionCurveServiceImpl;
	@Autowired
	private PetalProgressExtDao petalProgressExtDao;
	
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
	public RetentionCurve createNextProgress(Petal petal) {
		// TODO Auto-generated method stub
		return null;
	}

}

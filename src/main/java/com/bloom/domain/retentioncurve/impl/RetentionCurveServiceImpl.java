package com.bloom.domain.retentioncurve.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloom.annotation.RoleCheck;
import com.bloom.dao.ext.RetentionCurveExtDao;
import com.bloom.dao.po.RetentionCurve;
import com.bloom.dao.po.RetentionCurveExample;
import com.bloom.domain.CachedName;
import com.bloom.domain.gardener.meta.HighGradeRole;
import com.bloom.domain.retentioncurve.RetentionCurveService;
import com.bloom.exception.FlowBreakException;
/**
 * 记忆曲线实现类
 * @author 83554
 *
 */
@Service
public class RetentionCurveServiceImpl implements RetentionCurveService {
	@Autowired
	private RetentionCurveExtDao retentionCurveExtDao;

	@Override
	@Cacheable(cacheNames = CachedName.retentionCurve, key = "#curveId")
	public RetentionCurve findById(int curveId) {
		return Optional.ofNullable(retentionCurveExtDao.selectByPrimaryKey(curveId))
				.orElseThrow(() -> new FlowBreakException("资源不存在或已被删除！"));
	}
	
	@Override
	@Transactional
	@RoleCheck(value= {HighGradeRole.Administrator})
	@CacheEvict(cacheNames = CachedName.retentionCurve, allEntries = true)
	public RetentionCurve add(RetentionCurve curve) {
		curve.setCt(new Date());
		retentionCurveExtDao.insert(curve);
		return curve;
	}

	@Override
	@Transactional
	@RoleCheck(value= {HighGradeRole.Administrator})
	@CacheEvict(cacheNames = CachedName.retentionCurve, allEntries = true)
	public void delete(int curveId) {
		retentionCurveExtDao.deleteByPrimaryKey(curveId);
	}

	@Override
	@Cacheable(cacheNames = CachedName.retentionCurve, key = "#root.methodName")
	public List<RetentionCurve> enabledRetentionCurves() {
		RetentionCurveExample query = new RetentionCurveExample();
		query.createCriteria().andEnabledEqualTo(true);
		query.setOrderByClause("interval_minutes asc");
		return retentionCurveExtDao.selectByExample(query);
	}

	@Override
	@Cacheable(cacheNames = CachedName.retentionCurve, key = "#root.methodName")
	public List<RetentionCurve> retentionCurves() {
		RetentionCurveExample query = new RetentionCurveExample();
		query.setOrderByClause("interval_minutes asc");
		return retentionCurveExtDao.selectByExample(query);
	}

	
}

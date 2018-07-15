package com.bloom.domain.retentioncurve;
/**
 * 记忆曲线接口
 * @author 83554
 *
 */

import java.util.List;

import com.bloom.dao.po.RetentionCurve;

public interface RetentionCurveService {
	
	RetentionCurve findById(int curveId);
	
	RetentionCurve add(RetentionCurve curve);
	
	void delete(int curveId);
	
	List<RetentionCurve> enabledRetentionCurves();
	
	List<RetentionCurve> retentionCurves();
}

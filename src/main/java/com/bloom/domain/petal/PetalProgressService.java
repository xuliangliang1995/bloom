package com.bloom.domain.petal;

import java.util.Date;
import java.util.List;

import com.bloom.dao.po.Petal;
import com.bloom.dao.po.PetalProgress;

/**
 * 叶子-进展
 * @author xuliangliang
 *
 */
public interface PetalProgressService {
	
	void initProgress(Petal petal);
	
	PetalProgress createNextProgress(Petal petal);
	
	void fire(long progressId);
	
	List<Long> outdatedNoFireProgerssIdList();
	
	List<Integer> todayFiredPetalList(int gardenerId);
	
	List<Integer> todayFiredAndNoFiredPetalList(int gardenerId);
	
	void deletePetalProgressByPetalId(int petalId);
	
	List<PetalProgress> petalProgress(int gardenerId,Date startDate,Date endDate);
	
}

package com.bloom.domain.petal;

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
	
}

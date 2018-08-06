package com.bloom.domain.petal;

import com.bloom.dao.po.Petal;
import com.bloom.dao.po.RetentionCurve;

/**
 * 叶子-进展
 * @author xuliangliang
 *
 */
public interface PetalProgressService {
	
	void initProgress(Petal petal);
	
	RetentionCurve createNextProgress(Petal petal);

}

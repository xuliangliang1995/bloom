package com.bloom.domain.petal;

import com.bloom.dao.po.Petal;
import com.bloom.dao.po.PetalInnerTextWithBLOBs;

/**
 * 叶子 - text
 * @author xuliangliang
 *
 */
public interface PetalInnerTextService {
	
	PetalInnerTextWithBLOBs addPetalText(Petal petal, String text, String raw);
	
	PetalInnerTextWithBLOBs findByPetalId(int petalId);
	
	void deletePetalInnerText(int petalId);
	
	PetalInnerTextWithBLOBs editPetalText(Petal petal, String text, String raw);

}

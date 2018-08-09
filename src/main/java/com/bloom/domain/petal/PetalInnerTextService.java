package com.bloom.domain.petal;

import com.bloom.dao.po.Petal;
import com.bloom.dao.po.PetalInnerText;

/**
 * 叶子 - text
 * @author xuliangliang
 *
 */
public interface PetalInnerTextService {
	
	PetalInnerText addPetalText(Petal petal, String text);
	
	PetalInnerText findByPetalId(int petalId);

}

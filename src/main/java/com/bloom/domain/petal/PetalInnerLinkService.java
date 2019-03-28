package com.bloom.domain.petal;

import com.bloom.dao.po.Petal;
import com.bloom.dao.po.PetalInnerLink;

/**
 * 叶子 - link
 * @author xuliangliang
 *
 */
public interface PetalInnerLinkService {
	
	PetalInnerLink addPetalLink(Petal petal, String link);
	
	PetalInnerLink findByPetalId(int petalId);
	
	void deletePetalInnerLink(int petalId);

}

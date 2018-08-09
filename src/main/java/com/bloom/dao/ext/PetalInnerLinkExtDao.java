package com.bloom.dao.ext;

import com.bloom.dao.PetalInnerLinkMapper;
import com.bloom.dao.po.PetalInnerLink;

public interface PetalInnerLinkExtDao extends PetalInnerLinkMapper {
	
	PetalInnerLink findByPetalId(int petalId);

}

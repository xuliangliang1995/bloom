package com.bloom.dao.ext;

import com.bloom.dao.PetalInnerTextMapper;
import com.bloom.dao.po.PetalInnerTextWithBLOBs;

public interface PetalInnerTextExtDao extends PetalInnerTextMapper {
	
	PetalInnerTextWithBLOBs findByPetalId(int petalId);

}

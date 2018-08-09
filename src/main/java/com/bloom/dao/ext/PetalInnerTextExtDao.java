package com.bloom.dao.ext;

import com.bloom.dao.PetalInnerTextMapper;
import com.bloom.dao.po.PetalInnerText;

public interface PetalInnerTextExtDao extends PetalInnerTextMapper {
	
	PetalInnerText findByPetalId(int petalId);

}

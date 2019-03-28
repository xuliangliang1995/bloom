package com.bloom.dao.ext;

import com.bloom.dao.PetalProgressMapper;
import com.bloom.dao.po.PetalProgress;

public interface PetalProgressExtDao extends PetalProgressMapper {
	
	PetalProgress currentProgress(int petalId);
	
	void deletePetalProgress(int petalId);

}

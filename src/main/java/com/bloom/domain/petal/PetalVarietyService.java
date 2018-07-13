package com.bloom.domain.petal;

import java.util.List;

import com.bloom.dao.po.PetalVariety;

public interface PetalVarietyService {
	
	List<PetalVariety> varieties();
	
	PetalVariety findById(int varietyId);

}

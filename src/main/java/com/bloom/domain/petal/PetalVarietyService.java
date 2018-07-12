package com.bloom.domain.petal;

import java.util.List;
import java.util.Optional;

import com.bloom.dao.po.PetalVariety;

public interface PetalVarietyService {
	
	List<PetalVariety> varieties();
	
	Optional<PetalVariety> findById(int varietyId);

}

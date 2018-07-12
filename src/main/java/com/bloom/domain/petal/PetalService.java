package com.bloom.domain.petal;

import java.util.List;
import java.util.Optional;

import com.bloom.dao.po.Petal;

public interface PetalService {
	
	Optional<Petal> findByPetalId(int petalId);
	
	Petal add(Petal petal);
	
	List<Petal> flowerPetals(int flowerId);

}

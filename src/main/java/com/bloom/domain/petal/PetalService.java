package com.bloom.domain.petal;

import java.util.List;

import com.bloom.dao.po.Flower;
import com.bloom.dao.po.Petal;
import com.bloom.web.petal.vo.CreatePetalForm;

public interface PetalService {
	
	Petal findByPetalId(int petalId);
	
	Petal add(Flower flower,CreatePetalForm createPetalForm);
	
	List<Petal> flowerPetals(int flowerId);

}

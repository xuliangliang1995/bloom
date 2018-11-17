package com.bloom.domain.petal;

import java.util.List;

import com.bloom.dao.po.Flower;
import com.bloom.dao.po.Petal;
import com.bloom.util.mybatis.Page;
import com.bloom.web.petal.vo.CreatePetalForm;
import com.bloom.web.petal.vo.EditPetalForm;

public interface PetalService extends PetalInnerGroupService{
	
	Petal findByPetalId(int petalId);
	
	Petal add(Flower flower,CreatePetalForm createPetalForm);
	
	Petal edit(int petalId,Flower flower,EditPetalForm editPetalForm);
	
	List<Petal> flowerPetals(int flowerId,Page page);
	
	void deletePetal(int petalId,Flower flower);
	
}

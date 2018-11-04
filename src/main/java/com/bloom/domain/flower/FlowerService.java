package com.bloom.domain.flower;

import java.util.List;

import com.bloom.dao.po.Flower;
import com.bloom.util.mybatis.Page;
import com.bloom.web.flower.vo.CreateFlowerForm;
import com.bloom.web.flower.vo.EditFlowerForm;

public interface FlowerService {
	
	Flower create(int gardenerId, CreateFlowerForm form);
	
	Flower defaultFlower(int gardenerId);
	
	void deleteById(int gardenerId,int flowerId);
	
	Flower edit(int gardenerId,int flowerId,EditFlowerForm editFlowerForm);
	
	Flower findById(int id);
	
	List<Flower> findFlowerByGardener(int gardenerId,Page<Flower> page);

}

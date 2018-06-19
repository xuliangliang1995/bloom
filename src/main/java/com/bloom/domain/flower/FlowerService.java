package com.bloom.domain.flower;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.bloom.dao.po.Flower;

public interface FlowerService {
	
	Flower create(HttpServletRequest request,Flower flower);
	
	void deleteById(HttpServletRequest request,Integer id);
	
	void edit(HttpServletRequest request,Flower flower);
	
	Optional<Flower> findById(Integer id);
	
	List<Flower> findFlowerByGardener(Integer gardenerId);

}

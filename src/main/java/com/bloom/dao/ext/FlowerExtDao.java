package com.bloom.dao.ext;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bloom.dao.FlowerMapper;
import com.bloom.dao.po.Flower;
import com.bloom.util.mybatis.Page;

public interface FlowerExtDao extends FlowerMapper{
	
	List<Flower> findFlowersByGardenerId(@Param("gardenerId")int gardenerId,Page page);

}

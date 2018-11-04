package com.bloom.dao.ext;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bloom.dao.PetalMapper;
import com.bloom.dao.po.Petal;
import com.bloom.util.mybatis.Page;

public interface PetalExtDao extends PetalMapper {
	
	List<Petal> flowerPetals(@Param("flowerId")int flowerId,Page page);
	

}

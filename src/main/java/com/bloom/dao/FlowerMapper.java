package com.bloom.dao;

import com.bloom.dao.po.Flower;
import com.bloom.dao.po.FlowerExample;
import java.util.List;

public interface FlowerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Flower record);

    int insertSelective(Flower record);

    List<Flower> selectByExample(FlowerExample example);

    Flower selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Flower record);

    int updateByPrimaryKey(Flower record);
}
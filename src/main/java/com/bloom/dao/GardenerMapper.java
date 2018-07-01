package com.bloom.dao;

import java.util.List;

import com.bloom.dao.po.Gardener;
import com.bloom.dao.po.GardenerExample;

public interface GardenerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Gardener record);

    int insertSelective(Gardener record);

    List<Gardener> selectByExample(GardenerExample example);

    Gardener selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Gardener record);

    int updateByPrimaryKey(Gardener record);
}
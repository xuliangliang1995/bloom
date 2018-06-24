package com.bloom.dao;

import com.bloom.dao.po.Gardener;
import com.bloom.dao.po.GardenerExample;
import java.util.List;

public interface GardenerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Gardener record);

    int insertSelective(Gardener record);

    List<Gardener> selectByExample(GardenerExample example);

    Gardener selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Gardener record);

    int updateByPrimaryKey(Gardener record);
}
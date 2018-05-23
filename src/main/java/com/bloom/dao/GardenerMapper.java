package com.bloom.dao;

import com.bloom.dao.po.Gardener;
import com.bloom.dao.po.GardenerExample;
import java.util.List;

public interface GardenerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Gardener record);

    List<Gardener> selectByExample(GardenerExample example);

    Gardener selectByPrimaryKey(Integer id);

    Integer selectKeyByUsername(String username);
    
    int updateByPrimaryKey(Gardener record);
    
    int updateByPrimaryKeySelective(Gardener gardener);
}
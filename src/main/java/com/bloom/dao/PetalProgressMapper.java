package com.bloom.dao;

import com.bloom.dao.po.PetalProgress;
import com.bloom.dao.po.PetalProgressExample;
import java.util.List;

public interface PetalProgressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PetalProgress record);

    int insertSelective(PetalProgress record);

    List<PetalProgress> selectByExample(PetalProgressExample example);

    PetalProgress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PetalProgress record);

    int updateByPrimaryKey(PetalProgress record);
}
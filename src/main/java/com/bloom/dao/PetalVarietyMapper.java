package com.bloom.dao;

import com.bloom.dao.po.PetalVariety;
import com.bloom.dao.po.PetalVarietyExample;
import java.util.List;

public interface PetalVarietyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PetalVariety record);

    int insertSelective(PetalVariety record);

    List<PetalVariety> selectByExample(PetalVarietyExample example);

    PetalVariety selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PetalVariety record);

    int updateByPrimaryKey(PetalVariety record);
}
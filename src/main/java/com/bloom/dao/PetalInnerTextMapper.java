package com.bloom.dao;

import com.bloom.dao.po.PetalInnerText;
import com.bloom.dao.po.PetalInnerTextExample;
import java.util.List;

public interface PetalInnerTextMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PetalInnerText record);

    int insertSelective(PetalInnerText record);

    List<PetalInnerText> selectByExampleWithBLOBs(PetalInnerTextExample example);

    List<PetalInnerText> selectByExample(PetalInnerTextExample example);

    PetalInnerText selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PetalInnerText record);

    int updateByPrimaryKeyWithBLOBs(PetalInnerText record);

    int updateByPrimaryKey(PetalInnerText record);
}
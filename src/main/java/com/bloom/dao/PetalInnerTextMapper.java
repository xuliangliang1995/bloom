package com.bloom.dao;

import com.bloom.dao.po.PetalInnerText;
import com.bloom.dao.po.PetalInnerTextExample;
import com.bloom.dao.po.PetalInnerTextWithBLOBs;
import java.util.List;

public interface PetalInnerTextMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PetalInnerTextWithBLOBs record);

    int insertSelective(PetalInnerTextWithBLOBs record);

    List<PetalInnerTextWithBLOBs> selectByExampleWithBLOBs(PetalInnerTextExample example);

    List<PetalInnerText> selectByExample(PetalInnerTextExample example);

    PetalInnerTextWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PetalInnerTextWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(PetalInnerTextWithBLOBs record);

    int updateByPrimaryKey(PetalInnerText record);
}
package com.bloom.dao;

import com.bloom.dao.po.PetalInnerLink;
import com.bloom.dao.po.PetalInnerLinkExample;
import java.util.List;

public interface PetalInnerLinkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PetalInnerLink record);

    int insertSelective(PetalInnerLink record);

    List<PetalInnerLink> selectByExample(PetalInnerLinkExample example);

    PetalInnerLink selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PetalInnerLink record);

    int updateByPrimaryKey(PetalInnerLink record);
}
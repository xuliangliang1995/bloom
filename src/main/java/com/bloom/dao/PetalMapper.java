package com.bloom.dao;

import com.bloom.dao.po.Petal;
import com.bloom.dao.po.PetalExample;
import java.util.List;

public interface PetalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Petal record);

    int insertSelective(Petal record);

    List<Petal> selectByExample(PetalExample example);

    Petal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Petal record);

    int updateByPrimaryKey(Petal record);
}
package com.bloom.dao;

import com.bloom.dao.po.RetentionCurve;
import com.bloom.dao.po.RetentionCurveExample;
import java.util.List;

public interface RetentionCurveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RetentionCurve record);

    int insertSelective(RetentionCurve record);

    List<RetentionCurve> selectByExample(RetentionCurveExample example);

    RetentionCurve selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RetentionCurve record);

    int updateByPrimaryKey(RetentionCurve record);
}
package com.bloom.dao;

import com.bloom.dao.po.AliyunOssReference;
import com.bloom.dao.po.AliyunOssReferenceExample;
import java.util.List;

public interface AliyunOssReferenceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AliyunOssReference record);

    int insertSelective(AliyunOssReference record);

    List<AliyunOssReference> selectByExample(AliyunOssReferenceExample example);

    AliyunOssReference selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AliyunOssReference record);

    int updateByPrimaryKey(AliyunOssReference record);
}
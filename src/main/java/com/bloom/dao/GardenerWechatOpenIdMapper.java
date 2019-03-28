package com.bloom.dao;

import com.bloom.dao.po.GardenerWechatOpenId;
import com.bloom.dao.po.GardenerWechatOpenIdExample;
import java.util.List;

public interface GardenerWechatOpenIdMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GardenerWechatOpenId record);

    int insertSelective(GardenerWechatOpenId record);

    List<GardenerWechatOpenId> selectByExample(GardenerWechatOpenIdExample example);

    GardenerWechatOpenId selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GardenerWechatOpenId record);

    int updateByPrimaryKey(GardenerWechatOpenId record);
}
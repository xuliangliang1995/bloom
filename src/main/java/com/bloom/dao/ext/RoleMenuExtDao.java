package com.bloom.dao.ext;

import org.apache.ibatis.annotations.Param;

import com.bloom.dao.RoleMenuMapper;

public interface RoleMenuExtDao extends RoleMenuMapper {
	
	int deleteByMenuId(@Param("menuId")int menuId);

}

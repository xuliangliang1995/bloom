package com.bloom.dao.ext;

import org.apache.ibatis.annotations.Param;

import com.bloom.dao.RoleMapper;
import com.bloom.dao.po.Role;

public interface RoleExtDao extends RoleMapper{
	
	Role defaultRole();
	
	Integer getGardenerRoleId(@Param("gardenerId")int gardenerId);

}

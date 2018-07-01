package com.bloom.dao.ext;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bloom.dao.MenuMapper;
import com.bloom.domain.menu.vo.MenuTree;

public interface MenuExtDao extends MenuMapper{
	
	List<MenuTree> getMenuList(@Param("parentId")int parentId,@Param("roleId")int roleId);
	
	List<MenuTree> getAllMenuList(@Param("parentId")int parentId);

	int getChildMenuCount(@Param("parentId")int parentId);
}

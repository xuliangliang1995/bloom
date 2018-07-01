package com.bloom.domain.menu;

import java.util.List;

import com.bloom.dao.po.Menu;
import com.bloom.domain.menu.vo.MenuTree;

public interface MenuService {
	
	void createMenu(Menu menu);
	
	void deleteMenu(int menuId);
	
	List<MenuTree> roleMenuTree(int roleId);
	
	List<MenuTree> menuTree();

}

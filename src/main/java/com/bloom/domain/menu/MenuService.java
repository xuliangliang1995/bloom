package com.bloom.domain.menu;

import com.bloom.dao.po.Menu;
import com.bloom.domain.menu.vo.MenuTree;

public interface MenuService {
	
	void createMenu(Menu menu);
	
	void deleteMenu(Menu menu);
	
	MenuTree roleMenuTree(int roleId);
	
	MenuTree menuTree();

}

package com.bloom.domain.menu.vo;

import java.util.LinkedList;
import java.util.List;

import com.bloom.dao.po.Menu;

public class MenuTree extends Menu{
	private List<MenuTree> menu;

	public List<MenuTree> getMenu() {
		if(null == menu)
			return new LinkedList<MenuTree>();
		return menu;
	}

	public void setMenu(List<MenuTree> menu) {
		this.menu = menu;
	}

	
}

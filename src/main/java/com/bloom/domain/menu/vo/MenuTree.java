package com.bloom.domain.menu.vo;

import java.util.LinkedList;
import java.util.List;

import com.bloom.dao.po.Menu;

public class MenuTree extends Menu{
	private List<Menu> menu;

	public List<Menu> getMenu() {
		if(null == menu) {
			return new LinkedList<Menu>();
		}
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}
	
}

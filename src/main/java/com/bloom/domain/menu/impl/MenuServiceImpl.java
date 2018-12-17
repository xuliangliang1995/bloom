package com.bloom.domain.menu.impl;
import static com.bloom.util.general.NotNull.of;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
/**
 * 菜单管理
 */
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.bloom.annotation.RoleCheck;
import com.bloom.dao.ext.MenuExtDao;
import com.bloom.dao.ext.RoleMenuExtDao;
import com.bloom.dao.po.Menu;
import com.bloom.domain.CachedName;
import com.bloom.domain.gardener.meta.HighGradeRole;
import com.bloom.domain.menu.MenuService;
import com.bloom.domain.menu.vo.MenuTree;
import com.bloom.exception.FlowBreakException;
@Service
public class MenuServiceImpl implements MenuService {
	@Resource
	private MenuExtDao menuExtDao;
	@Resource
	private RoleMenuExtDao roleMenuExtDao;
	
	@Override
	@Transactional
	@RoleCheck(HighGradeRole.Administrator)
	@CacheEvict(cacheNames = CachedName.MENU_TREE,allEntries = true)
	public void createMenu(Menu menu) {
		Date now = new Date();
		Assert.isTrue(StringUtils.hasText(menu.getName()),"菜单名称不能为空");
		int parentId = of(menu.getParentId());
		if(parentId > 0) {
			Menu parent = menuExtDao.selectByPrimaryKey(parentId);
			Assert.notNull(parent,"创建失败：父菜单不存在或已被删除！");
		}
		menu.setCt(now);
		menuExtDao.insert(menu);
	}

	@Override
	@Transactional
	@RoleCheck(HighGradeRole.Administrator)
	@CacheEvict(cacheNames = CachedName.MENU_TREE,allEntries = true)
	public void deleteMenu(int menuId) {
		Menu menu = Optional.ofNullable(
				menuExtDao.selectByPrimaryKey(menuId)
				).orElseThrow(() -> new FlowBreakException("所选菜单不存在或已被删除！"));
		int childMenuCount = menuExtDao.getChildMenuCount(menu.getId());
		
		Assert.isTrue(childMenuCount == 0,"请先删除子菜单！");
		roleMenuExtDao.deleteByMenuId(menuId);
		menuExtDao.deleteByPrimaryKey(menuId);
	}

	@Override
	@Cacheable(cacheNames = CachedName.MENU_TREE, key = "#roleId")
	public List<MenuTree> roleMenuTree(int roleId) {
		List<MenuTree> tags = menuExtDao.getMenuList(0, roleId);
		tags.stream().forEach(tag -> {
			List<MenuTree> menus = menuExtDao.getMenuList(tag.getParentId(), roleId);
			menus.stream().forEach(menu -> {
				List<MenuTree> subMenus = menuExtDao.getMenuList(menu.getParentId(), roleId);
				menu.setMenu(subMenus);
			});
			tag.setMenu(menus);
		});
		return tags;
	}

	@Override
	@Cacheable(cacheNames = CachedName.MENU_TREE,key = "#root.methodName")
	public List<MenuTree> menuTree() {
		List<MenuTree> tags = menuExtDao.getAllMenuList(0);
		tags.stream().forEach(tag -> {
			List<MenuTree> menus = menuExtDao.getAllMenuList(tag.getParentId());
			menus.stream().forEach(menu -> {
				List<MenuTree> subMenus = menuExtDao.getAllMenuList(menu.getParentId());
				menu.setMenu(subMenus);
			});
			tag.setMenu(menus);
		});
		return tags;
	}

}

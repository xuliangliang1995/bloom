package com.bloom.domain.menu.impl;
import static com.bloom.util.general.NotNull.of;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 菜单管理
 */
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.testng.Assert;

import com.bloom.annotation.RoleCheck;
import com.bloom.dao.ext.MenuExtDao;
import com.bloom.dao.po.Menu;
import com.bloom.domain.gardener.meta.HighGradeRole;
import com.bloom.domain.menu.MenuService;
import com.bloom.domain.menu.vo.MenuTree;
@Service
public class MenuServiceImpl implements MenuService {
	@Resource
	private MenuExtDao menuExtDao;
	
	@Override
	@RoleCheck(HighGradeRole.Administrator)
	@Transactional
	public void createMenu(Menu menu) {
		Date now = new Date();
		Assert.assertTrue(StringUtils.hasText(menu.getName()),"菜单名称不能为空");
		int parentId = of(menu.getParentId());
		if(parentId > 0) {
			Menu parent = menuExtDao.selectByPrimaryKey(parentId);
			Assert.assertNotNull(parent,"创建失败：父菜单不存在或已被删除！");
		}
		menu.setCt(now);
		menuExtDao.insert(menu);
	}

	@Override
	@RoleCheck(HighGradeRole.Administrator)
	@Transactional
	public void deleteMenu(Menu menu) {
		// TODO Auto-generated method stub

	}

	@Override
	public MenuTree roleMenuTree(int roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MenuTree menuTree() {
		// TODO Auto-generated method stub
		return null;
	}

}

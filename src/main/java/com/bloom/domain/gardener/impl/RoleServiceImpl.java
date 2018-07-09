package com.bloom.domain.gardener.impl;

import java.util.Date;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.bloom.annotation.RoleCheck;
import com.bloom.dao.ext.GardenerExtDao;
import com.bloom.dao.ext.RoleExtDao;
import com.bloom.dao.po.Role;
import com.bloom.domain.gardener.RoleService;
import com.bloom.domain.gardener.meta.HighGradeRole;
import com.bloom.exception.FlowBreakException;
/**
 * 角色管理
 * @author 83554
 *
 */
@Service
public class RoleServiceImpl implements RoleService {
	@Resource
	private RoleExtDao roleExtDao;
	@Resource
	private GardenerExtDao gardenerExtDao;
	
	@Override
	public Role defaultRole() {
		Role role = roleExtDao.defaultRole();
		Assert.notNull(role,"没有设置默认角色！");
		return role;
	}

	@Override
	public Role getGardenerRole(int gardenerId) {
		int roleId = Optional.ofNullable(
				roleExtDao.getGardenerRoleId(gardenerId)
				).orElse(0);
		return Optional.ofNullable(
				roleExtDao.selectByPrimaryKey(roleId)
				).orElse(this.defaultRole());
	}

	@Override
	@RoleCheck(HighGradeRole.Administrator)
	@Transactional
	public Role createRole(Role role) {
		Date now =  new Date();
		Assert.isTrue(StringUtils.hasText(role.getName()),"角色名不能为空");
		Assert.isNull(role.getId(),"参数有误");
		role.setCt(now);
		role.setOrdinary(false);
		roleExtDao.insert(role);
		return role;
	}

	@Override
	@RoleCheck(HighGradeRole.Administrator)
	public void deleteRole(int roleId) {
		throw new FlowBreakException("暂不支持此操作！");
	}

	@Override
	public Role getRoleById(int id) {
		return Optional.ofNullable(
				roleExtDao.selectByPrimaryKey(id)
				).orElseThrow(() -> new FlowBreakException("该角色不存在或已被删除！"));
	}

}

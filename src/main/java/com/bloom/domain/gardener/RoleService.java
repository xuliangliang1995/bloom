package com.bloom.domain.gardener;

import com.bloom.dao.po.Role;

public interface RoleService {
	
	Role defaultRole();
	
	Role getGardenerRole(int gardenerId);
	
	Role getRoleById(int id);
	
	Role createRole(Role role);
	
	void deleteRole(int roleId);
}

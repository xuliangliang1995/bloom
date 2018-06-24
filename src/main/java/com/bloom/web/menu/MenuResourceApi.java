package com.bloom.web.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloom.dao.po.Role;
import com.bloom.domain.gardener.RoleService;
import com.bloom.domain.menu.MenuService;
/**
 * 菜单
 * @author 83554
 *
 */
@RestController
@RequestMapping("/gardener/{gardenerId}/menus")
public class MenuResourceApi {
	@Autowired
	private MenuService menuServiceImpl;
	@Autowired
	private RoleService roleServiceImpl;
	
	@GetMapping
	public ResponseEntity<?> menuTree(@PathVariable int gardenerId){
		Role role = roleServiceImpl.getGardenerRole(gardenerId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(menuServiceImpl.roleMenuTree(role.getId()));
	}

}

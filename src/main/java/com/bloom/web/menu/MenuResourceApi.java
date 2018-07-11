package com.bloom.web.menu;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloom.dao.po.Menu;
import com.bloom.dao.po.Role;
import com.bloom.domain.gardener.RoleService;
import com.bloom.domain.menu.MenuService;
import com.bloom.domain.menu.vo.MenuTree;
import com.bloom.util.general.NotNull;
import com.bloom.web.menu.vo.CreateMenuDTO;
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
	
	@PostMapping
	public ResponseEntity<?> createMenu(@Validated CreateMenuDTO createMenuDTO,BindingResult result){
		Menu menu = new Menu();
		menu.setParentId(NotNull.of(createMenuDTO.getParentId()));
		menu.setIcon(createMenuDTO.getIcon());
		menu.setName(createMenuDTO.getName());
		menu.setTarget(createMenuDTO.getTarget());
		menu.setCt(new Date());
		menuServiceImpl.createMenu(menu);
		return ResponseEntity.status(HttpStatus.OK)
				.body(menuServiceImpl.menuTree());
	}
}

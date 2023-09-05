package com.example.demo.controller.admin;

import com.example.demo.domain.dto.RoleDto;
import com.example.demo.domain.entity.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private RoleRepository repository;

	@GetMapping(value="/admin/roles")
	public String getRoles(Model model) throws Exception {

		List<Role> roles = roleService.getRoles();
		model.addAttribute("roles", roles);

		return "admin/role/list";
	}

	@GetMapping(value="/admin/roles/register")
	public String viewRoles(Model model) throws Exception {

		RoleDto role = new RoleDto();
		model.addAttribute("role", role);

		return "admin/role/detail";
	}

	@PostMapping(value="/admin/roles")
	public String createRole(RoleDto roleDto) throws Exception {
		Role parentRole = repository.findByRoleName(roleDto.getParentRoleName());
		if(StringUtils.hasText(roleDto.getParentRoleName()) && parentRole == null){
			throw new NoSuchElementException("No such a role");
		}
		Role role = Role.createRole(roleDto);
		role.setParentRole(parentRole);
		if (parentRole!=null){
			parentRole.getChildRoles().add(role);
		}

		roleService.createRole(role);

		return "redirect:/admin/roles";
	}

	@GetMapping(value="/admin/roles/{id}")
	public String getRole(@PathVariable String id, Model model) throws Exception {

		Role role = roleService.getRole(Long.valueOf(id));
		List<RoleDto> roles = roleService.getRoles()
				.stream().filter(e->!e.equals(role))
				.map(RoleDto::createRoleDto).collect(Collectors.toList());
		RoleDto roleDto = RoleDto.createRoleDto(role);
		model.addAttribute("roles",roles);
		model.addAttribute("role", roleDto);

		return "admin/role/detail";
	}

	@GetMapping(value="/admin/roles/delete/{id}")
	public String removeResources(@PathVariable String id, Model model) throws Exception {

		Role role = roleService.getRole(Long.valueOf(id));
		roleService.deleteRole(Long.valueOf(id));

		return "redirect:/admin/resources";
	}
}

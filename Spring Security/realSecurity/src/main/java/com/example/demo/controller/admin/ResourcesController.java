package com.example.demo.controller.admin;



import com.example.demo.domain.dto.ResourcesDto;
import com.example.demo.domain.entity.Resources;
import com.example.demo.domain.entity.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.ResourcesService;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
public class ResourcesController {
	
	@Autowired
	private ResourcesService resourcesService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RoleService roleService;

	@GetMapping(value="/admin/resources")
	public String getResources(Model model) throws Exception {

		List<Resources> resources = resourcesService.getResources();
		model.addAttribute("resources", resources);

		return "admin/resource/list";
	}

	@PostMapping(value="/admin/resources")
	public String createResources(ResourcesDto resourcesDto) throws Exception {

		Role role = roleRepository.findByRoleName(resourcesDto.getRoleName());
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		Resources resources = Resources.createResources(resourcesDto);
		resources.setRoleSet(roles);

		resourcesService.createResources(resources);

		return "redirect:/admin/resources";
	}

	@GetMapping(value="/admin/resources/register")
	public String viewRoles(Model model) throws Exception {

		List<Role> roleList = roleService.getRoles();
		model.addAttribute("roleList", roleList);

		ResourcesDto resources = new ResourcesDto();
		Set<Role> roleSet = new HashSet<>();
		roleSet.add(new Role());
		resources.setRoleSet(roleSet);
		model.addAttribute("resources", resources);

		return "admin/resource/detail";
	}

	@GetMapping(value="/admin/resources/{id}")
	public String getResources(@PathVariable String id, Model model) throws Exception {

		List<Role> roleList = roleService.getRoles();
        model.addAttribute("roleList", roleList);
		Resources resources = resourcesService.getResources(Long.valueOf(id));


		ResourcesDto resourcesDto = ResourcesDto.createResourcesDto(resources);
		model.addAttribute("resources", resourcesDto);

		return "admin/resource/detail";
	}

	@GetMapping(value="/admin/resources/delete/{id}")
	public String removeResources(@PathVariable String id, Model model) throws Exception {

		Resources resources = resourcesService.getResources(Long.valueOf(id));
		resourcesService.deleteResources(Long.valueOf(id));

		return "redirect:/admin/resources";
	}
}

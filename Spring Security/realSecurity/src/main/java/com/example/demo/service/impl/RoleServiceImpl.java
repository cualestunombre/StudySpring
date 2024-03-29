package com.example.demo.service.impl;


import com.example.demo.domain.entity.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public Role getRole(long id) {
        return roleRepository.findById(id).orElse(new Role());
    }

    @Transactional
    public List<Role> getRoles() {

        return roleRepository.findAll();
    }

    @Transactional
    public void createRole(Role role){
        Role findRole = roleRepository.findByRoleName(role.getRoleName());
        if (findRole == null){roleRepository.save(role);}
        else{
            findRole.setRoleDesc(role.getRoleDesc());
            findRole.setParentRole(role.getParentRole());
        }


    }

    @Transactional
    public void deleteRole(long id) {
        roleRepository.deleteById(id);
    }
}
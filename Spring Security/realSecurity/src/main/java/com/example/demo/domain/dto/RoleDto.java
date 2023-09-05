
package com.example.demo.domain.dto;

import com.example.demo.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto{

    private String id;
    private String roleName;
    private String roleDesc;

    private String parentRoleName;


    static public RoleDto createRoleDto(Role role){
        RoleDto roleDto = new RoleDto();
        roleDto.setParentRoleName(role.getParentRole() == null ? null : role.getParentRole().getRoleName());
        roleDto.setRoleName(role.getRoleName());
        roleDto.setRoleDesc(role.getRoleDesc());
        return roleDto;
    }

}



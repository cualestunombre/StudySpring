
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

    static public RoleDto createRoleDto(Role role){
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleName(role.getRoleName());
        roleDto.setRoleDesc(role.getRoleDesc());
        return roleDto;
    }

}



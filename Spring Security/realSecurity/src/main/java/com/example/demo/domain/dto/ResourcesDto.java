package com.example.demo.domain.dto;


import com.example.demo.domain.entity.Resources;
import com.example.demo.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourcesDto{

    private Long id;
    private String resourceName;
    private String httpMethod;
    private int orderNum;
    private String resourceType;

    private String roleName;

    private Set<Role> roleSet;

    static public ResourcesDto createResourcesDto(Resources resources){
        ResourcesDto resourcesDto = new ResourcesDto();
        resourcesDto.setId(resources.getId());
        resourcesDto.setRoleSet(resources.getRoleSet());
        resourcesDto.setResourceName(resources.getResourceName());
        resourcesDto.setResourceType(resources.getResourceType());
        resourcesDto.setHttpMethod(resources.getHttpMethod());
        resourcesDto.setOrderNum(resources.getOrderNum());
        return resourcesDto;
    }

}

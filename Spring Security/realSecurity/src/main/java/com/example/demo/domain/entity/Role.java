
package com.example.demo.domain.entity;

import com.example.demo.domain.dto.RoleDto;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "ROLE")
@Getter
@Setter
@ToString(exclude = {"users","resourcesSet"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;


    @Column(name = "role_name",unique = true)
    private String roleName;

    @Column(name = "role_desc")
    private String roleDesc;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roleSet")
    @OrderBy("orderNum asc")
    private Set<Resources> resourcesSet = new LinkedHashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "userRoles")
    private Set<Account> accounts = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Role parentRole;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "parentRole")
    private List<Role> childRoles = new ArrayList<>();


    static public Role createRole(RoleDto roleDto){
        Role role = new Role();
        role.setRoleDesc(roleDto.getRoleDesc());
        role.setRoleName(roleDto.getRoleName());
        return role;
    }

}



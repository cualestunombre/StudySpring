package com.example.demo.repository;


import com.example.demo.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String name);

    @Override
    void delete(Role role);

}

package com.example.demo.domain.entity;

import com.example.demo.domain.dto.AccountDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.core.annotation.Order;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
@EqualsAndHashCode(of = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String email;
    private int age;




    @ManyToMany(fetch = FetchType.EAGER, cascade={CascadeType.ALL})
    @JoinTable(name = "account_roles", joinColumns = { @JoinColumn(name = "account_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id") })
    private Set<Role> userRoles = new HashSet<>();


    public static Account createAccount(AccountDto dto){
        Account account = new Account();
        account.setPassword(dto.getPassword());
        account.setAge(dto.getAge());
        account.setEmail(dto.getEmail());
        account.setUsername(dto.getUsername());
        return account;
    }
}

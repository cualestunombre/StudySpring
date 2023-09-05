package com.example.demo.domain.dto;

import com.example.demo.domain.entity.Account;
import com.example.demo.domain.entity.Role;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class AccountDto {

    private String id;
    private List<String> roles;
    private String username;
    private String password;
    private String email;
    private int age;

    static public AccountDto createAccountDto(Account account){
        AccountDto accountDto = new AccountDto();
        accountDto.setRoles(account.getUserRoles().stream().map(Role::getRoleName).collect(Collectors.toList()));
        accountDto.setId(accountDto.getId());
        accountDto.setAge(account.getAge());
        accountDto.setPassword(account.getPassword());
        accountDto.setEmail(account.getEmail());
        accountDto.setUsername(account.getUsername());
        return accountDto;
    }
}

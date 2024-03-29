package com.example.demo.service;



import com.example.demo.domain.dto.AccountDto;
import com.example.demo.domain.entity.Account;

import java.util.List;

public interface UserService {

    void createUser(Account account);

    void modifyUser(AccountDto accountDto);

    List<Account> getUsers();

    AccountDto getUser(Long id);

    Account getUserByName(String name);

    void deleteUser(Long idx);
}

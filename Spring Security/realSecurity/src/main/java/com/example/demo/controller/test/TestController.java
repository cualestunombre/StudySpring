package com.example.demo.controller.test;

import com.example.demo.domain.entity.Account;
import com.example.demo.domain.entity.Role;
import com.example.demo.service.UserService;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.Set;

@Controller
public class TestController {
    @Autowired
    UserService userService;

    @ResponseBody
    @GetMapping("/test")
    public Set<String> test(){
        Account account = userService.getUsers().stream().findFirst().orElseThrow(()->new IllegalArgumentException());
        Set<String> roles = new HashSet<>();
        account.getUserRoles().stream().forEach(e->roles.add(e.getRoleName()));
        return roles;
    }
}

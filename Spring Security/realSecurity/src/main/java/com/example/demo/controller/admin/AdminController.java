package com.example.demo.controller.admin;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping(value="/admin")
    public String home() throws Exception {
        return "admin/home";
    }

}

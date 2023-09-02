package com.example.demo.controller.api;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @GetMapping("/api/messages")
    public String message(){return "message";}
    @GetMapping("/api/test")
    public String test(){
        return "test";
    }
}

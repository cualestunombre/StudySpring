package hello.small.controller;

import hello.small.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MainPageController {
    private final ProductService productService;
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/main")
    public String main(Authentication a, Model model){
        model.addAttribute("username",a.getName());
        model.addAttribute("products",productService.findAll());
        return "main";
    }
    @ResponseBody
    @GetMapping("/api")
    public String getHello(){
        return "hello!";
    }
    @PostMapping("/api")
    @ResponseBody
    public String sayHello(CsrfToken csrfToken){
        return "postHello!";
    }

}

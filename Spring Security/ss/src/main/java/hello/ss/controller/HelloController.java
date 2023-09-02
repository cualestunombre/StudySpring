package hello.ss.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@RestController
public class HelloController {
    @GetMapping("/hello")
    //스프링 argumentResolver 가 injectedA를 주입한다
    public String hello(HttpServletRequest req,HttpServletResponse res,Authentication injectedA){

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication a = context.getAuthentication();


        res.setStatus(201);
        return "Hello "+ injectedA.getName()+"your password is " + a.getCredentials();
    }


    // TheadLocal 설정으로는 인증 정보가 공유되지 않는다
    @Async
    @GetMapping("/bye")
    public void goodbye(){
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        System.out.println(username);
    }
    @GetMapping("/home")
    public String hello(HttpServletRequest req){

        return "hello";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/video/{contents}/en")
    public String enVideo(@PathVariable String contents){

        return contents;
    }

    @PostMapping("/hello")
    public String postHello(){

        return "Post Hello!";
    }
    @PostMapping("/ciao")
    public String postCiao(){return "yes!";}
}

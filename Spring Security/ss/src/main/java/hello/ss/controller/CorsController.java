package hello.ss.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class CorsController {
    @GetMapping("/cors")
    public String getCors(HttpServletRequest req, HttpServletResponse res, CsrfToken csrfToken){

        return "cors";
    }
    @PostMapping("/cors")
    @ResponseBody
    public String postCors(HttpServletRequest req, HttpServletResponse res){
        return "ok";
    }
}

package hello.project.controller;

import hello.project.domain.Member;
import hello.project.domain.Otp;
import hello.project.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final MemberService memberService;

    @PostMapping("/member/add")
    public void addUser(@RequestBody Member member){
        memberService.addMember(member);
    }

    @PostMapping("/member/auth")
    public void auth(@RequestBody Member member){
        memberService.auth(member);
    }

    @PostMapping("/otp/check")
    public void Check(@RequestBody Otp otp, HttpServletResponse response){
        if (memberService.check(otp)){
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else{
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}

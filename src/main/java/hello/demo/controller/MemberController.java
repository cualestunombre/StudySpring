package hello.demo.controller;
import org.springframework.stereotype.Controller;
import hello.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

@Controller //Controller annotation이 붙어 있으면 컨테이너에 빈으로 관리 된다
public class MemberController {
    private final MemberService memberService;
    // private final MemberService = new MemberService();형태도 가능함
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService=memberService;
    }
}

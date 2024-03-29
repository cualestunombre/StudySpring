package hello.login.web;

import hello.login.domain.member.MemberRepository;
import hello.login.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import hello.login.domain.member.Member;
import hello.login.web.session.SessionManager;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    public String home() {
        return "home";
    }
    @GetMapping("/")
    public String homeLoginV3ArgumentResolver(@Login Member loginMember, Model model){
        if(loginMember==null){ return "home";}
        else{
            System.out.println(loginMember);
        }
        model.addAttribute("member",loginMember);
        return "loginHome";
    }
//    @GetMapping("/")
//    public String homeLoginV3Spring(@SessionAttribute(name=SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
//                                    Model model){
//        if(loginMember==null) return "home";
//
//        model.addAttribute("member",loginMember);
//        return "loginHome";
//    }
//    @GetMapping("/")
//    public String homeLoginV3(HttpServletRequest req,Model model){
//        HttpSession session = req.getSession(false);
//        if(session==null) return "home";
//
//        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
//        if(loginMember==null) {session.invalidate(); return "home";}
//        model.addAttribute("member",loginMember);
//        return "loginHome";
//
//    }
//    @GetMapping("/")
//    public String homeLoginV2(HttpServletRequest req, Model model){
//        Member member = (Member)sessionManager.getSession(req);
//        if(member==null) return"home";
//        model.addAttribute("member",member);
//        return "loginHome";
//    }
//    @GetMapping("/")
//    public String homeLogin(@CookieValue(name="memberId",required = false) Long memberId, Model model){
//        if(memberId==null){
//            return "home";
//        }
//        Member member = memberRepository.findById(memberId);
//        if(member==null){
//            return "home";
//        }
//        model.addAttribute("member",member);
//        return "loginHome";
//    }


}
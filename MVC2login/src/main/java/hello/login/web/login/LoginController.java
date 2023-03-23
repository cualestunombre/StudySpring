package hello.login.web.login;

import hello.login.domain.login.LoginService;
import hello.login.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import hello.login.domain.member.Member;
import hello.login.web.session.SessionManager;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final LoginService loginService;
    private final SessionManager sessionManager;
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form){
        return "login/loginForm";
    }
    @PostMapping("/login")
    public String loginV4(@Validated @ModelAttribute LoginForm form, BindingResult bindingResult, @RequestParam(defaultValue = "/")
    String redirectURL, HttpServletRequest req){
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }
        Member loginMember = loginService.login(form.getLoginId(),form.getPassword());
        log.info("login? {}",loginMember);

        if(loginMember==null){
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다");
            return "login/loginForm";
        }

        HttpSession session = req.getSession();

        session.setAttribute(SessionConst.LOGIN_MEMBER,loginMember);

        return "redirect:"+redirectURL;
    }
//    @PostMapping("/login")
//    public String loginV3(@Validated @ModelAttribute LoginForm loginForm,BindingResult bindingResult, HttpServletRequest req){
//        if(bindingResult.hasErrors()){
//            return "login/loginForm";
//        }
//        Member loginMember = loginService.login(loginForm.getLoginId(),loginForm.getPassword());
//        log.info("login? {}",loginMember);
//
//        if(loginMember==null){
//            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다");
//            return "login/loginForm";
//        }
//        HttpSession session = req.getSession();
//        session.setAttribute(SessionConst.LOGIN_MEMBER,loginMember);
//        return "redirect:/";
//    }

//    @PostMapping("/login")
//    public String loginV2(@Validated @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse response){
//        if(bindingResult.hasErrors()) return "login/loginForm";
//
//        Member loginMember = loginService.login(form.getLoginId(),form.getPassword());
//        log.info("login {}",loginMember);
//        if(loginMember==null){
//            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다");
//            return "login/loginForm";
//        }
//        sessionManager.createSession(loginMember,response);
//        return "redirect:/";
//    }
//    @PostMapping("/login")
//    public String login(@Validated @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletResponse res){
//        if(bindingResult.hasErrors()){
//            return "login/loginForm";
//        }
//
//        Member loginMember = loginService.login(loginForm.getLoginId(),loginForm.getPassword());
//        log.info("login? ={}",loginMember);
//
//        if(loginMember==null){
//            bindingResult.reject("loginFail","아이디ㄷ 또는 비밀번호가 맞지 않습니다");
//            return "login/loginForm";
//        }
//        Cookie cookie = new Cookie("memberId",String.valueOf(loginMember.getId()));
//        res.addCookie(cookie);
//
//
//        return "redirect:/";
//
//    }
    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        if(session!=null){
            session.invalidate();
        }
        return "redirect:/";
    }
//    @PostMapping("/logout")
//    public String logoutV2(HttpServletRequest req){
//        sessionManager.expire(req);
//        return "redirect:/";
//    }
//    @PostMapping("/logout")
//    public String logout(HttpServletResponse res){
//        expireCookie(res,"memberId");
//        return "redirect:/";
//    }
    private void expireCookie(HttpServletResponse res, String cookieName){
        Cookie cookie = new Cookie(cookieName,null);
        cookie.setMaxAge(0);
        res.addCookie(cookie);
    }
}

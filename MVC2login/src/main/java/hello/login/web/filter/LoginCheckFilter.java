package hello.login.web.filter;

import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {
    private static final String[] whiteList={"/","/members/add","/login","/logout","/css/*"};
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException{
        HttpServletRequest httpReq = (HttpServletRequest) req;
        String requestURI = httpReq.getRequestURI();

        HttpServletResponse httpRes = (HttpServletResponse) res;

        try{
            log.info("인증 체크 필터 시작 {}",requestURI);
            if(isLoginCheckPath(requestURI)){
                log.info("인증 체크 로직 실행{}",requestURI);
                HttpSession session = httpReq.getSession(false);
                if(session==null||session.getAttribute(SessionConst.LOGIN_MEMBER)==null){
                    log.info("미인증 사용자 요청 {}",requestURI);
                    httpRes.sendRedirect("/login?redirectURL="+requestURI);
                    return ;
                }
            }
            chain.doFilter(req,res);
        }catch(Exception e){
            throw e;
        }finally {
            log.info("인증 체크 필터 종료 {}",requestURI);
        }

    }
    private boolean isLoginCheckPath(String requestURI){
        return !PatternMatchUtils.simpleMatch(whiteList,requestURI); //white리스트에 속하면 false else true
    }
}

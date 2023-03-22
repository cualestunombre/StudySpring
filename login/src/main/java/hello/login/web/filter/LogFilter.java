package hello.login.web.filter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
        log.info("log filter init");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException{
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        String requestURI = httpRequest.getRequestURI();
        String method = httpRequest.getMethod().toString();
        String uuid = UUID.randomUUID().toString();
        try{
            log.info("REQUEST {} {} {}",uuid,requestURI,method);

            chain.doFilter(req,res); //이거 없으면 Servelet도 호출이 안됨
        }catch (Exception e){
            throw e;
        }finally {
            log.info("RESPONSE {} {} {}",uuid,requestURI,method);
        }
    }
    @Override
    public void destroy(){
        log.info("log filter destroy");
    }
}

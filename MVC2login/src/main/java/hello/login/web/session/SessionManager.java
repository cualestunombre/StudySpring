package hello.login.web.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class SessionManager {
    public static final String SESSION_COOKIE_NAME = "mySessionId";

    private Map<String,Object> sessionStore = new ConcurrentHashMap<String, Object>() ;


    public void createSession(Object value, HttpServletResponse res){
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId,value);
        Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME,sessionId);
        res.addCookie(mySessionCookie);
    }

    public Object getSession(HttpServletRequest req){
        Cookie sessionCookie = findCookie(req,SESSION_COOKIE_NAME);
        if(sessionCookie==null) return null;
        return sessionStore.get(sessionCookie.getValue());
    }
    public void expire(HttpServletRequest req){
        Cookie sessionCookie = findCookie(req, SESSION_COOKIE_NAME);
        if(sessionCookie!=null) sessionStore.remove(sessionCookie.getValue());
    }
    private Cookie findCookie(HttpServletRequest req, String cookieName){
        if(req.getCookies()==null) return null;
        return Arrays.stream(req.getCookies()).filter(cookie->cookie.getName().equals(cookieName)).findAny().orElse(null);
    }
}

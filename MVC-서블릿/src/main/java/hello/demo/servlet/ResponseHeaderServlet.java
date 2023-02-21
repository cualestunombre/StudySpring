package hello.demo.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name="responseHeaderServlet",urlPatterns ="/response-redirect")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setStatus(200);
        res.setHeader("Content-Type","text/plain;charset=utf-8");
        res.setHeader("Cache-Control","no-cache");
        cookie(res);
        redirect(res);

    }
    private void cookie(HttpServletResponse res){
        Cookie cookie = new Cookie("myCookie","good");
        cookie.setMaxAge(600);
        res.addCookie(cookie);
    }
    private void redirect(HttpServletResponse res) throws IOException{
        res.sendRedirect("/");
    }
}

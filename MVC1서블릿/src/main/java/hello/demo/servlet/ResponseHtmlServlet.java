package hello.demo.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.demo.domain.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="responseHtmlServlet",urlPatterns = "/response-html")
public class ResponseHtmlServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
       res.setHeader("content-type","text/html");
        res.setCharacterEncoding("utf-8");
       res.getWriter().println("<html>");
       res.getWriter().println("<body>");
       res.getWriter().println("<div>");
       res.getWriter().println("하하하하하하");
       res.getWriter().println("</div>");
       res.getWriter().println("</body>");
       res.getWriter().println("</html>");




    }
}

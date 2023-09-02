package hello.demo.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="requestParamServelt", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.getParameterNames().asIterator().forEachRemaining((ele)->{
            System.out.println(ele+":"+req.getParameter(ele));
        });
        System.out.println("복수 파라미터 조회");
        String [] usernames = req.getParameterValues("usernames");
        for(String name : usernames){
            System.out.println(name);
        }
    }

}

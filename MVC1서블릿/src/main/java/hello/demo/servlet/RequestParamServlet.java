package hello.demo.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="requestParamServelt", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res){
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

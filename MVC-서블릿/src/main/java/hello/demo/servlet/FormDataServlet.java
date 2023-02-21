package hello.demo.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="formDataServelt",urlPatterns = "/request-formdata")
public class FormDataServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res){
        req.getParameterNames().asIterator().forEachRemaining((ele)->{
            System.out.println(ele+":"+req.getParameter(ele));
        });

    }
}

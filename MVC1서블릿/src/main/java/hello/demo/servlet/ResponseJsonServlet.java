package hello.demo.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.demo.domain.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="responseJsonServlet",urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {
    private ObjectMapper obj = new ObjectMapper();
    @Override
    protected void service(HttpServletRequest req,HttpServletResponse res) throws ServletException{
        HelloData helloData = new HelloData();
        helloData.setAge(26);
        helloData.setName("seok-woo");
        res.setHeader("content-type","application/json");
        String result=null;
        try{
            result = obj.writeValueAsString(helloData);
            res.getWriter().write(result);
        }
        catch (IOException e){
            System.out.println( e.getMessage());
        }




    }
}

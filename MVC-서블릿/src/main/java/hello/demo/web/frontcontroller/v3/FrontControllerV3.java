package hello.demo.web.frontcontroller.v3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import hello.demo.web.frontcontroller.ModelView;
import hello.demo.web.frontcontroller.MyView;

@WebServlet(name="frontControllerV3",urlPatterns = "/front-contoller/v3/*")
public class FrontControllerV3 extends HttpServlet {
    private Map<String,ControllerV3> controllerV3Map = new HashMap<>();
    public FrontControllerV3(){
        controllerV3Map.put("/front-contoller/v3/new-form",new MemberFormControllerV3());
        controllerV3Map.put("/front-contoller/v3/save",new MemberSaveControllerV3());
        controllerV3Map.put("/front-contoller/v3/members",new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String uri = req.getRequestURI();
        ControllerV3 controller = controllerV3Map.get(uri);
        if (controller==null){
            res.setStatus(404);
            return;
        }
        Map<String,String> paramMap = createParamMap(req);
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);
        view.render(mv.getModel(),req,res);
    }
    private MyView viewResolver(String viewName){
        return new MyView("/WEB-INF/views/"+viewName+".jsp");
    }
    private Map<String, String> createParamMap(HttpServletRequest req){
        Map<String,String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator().forEachRemaining(paramname->paramMap.put(paramname,req.getParameter(paramname)));
        return paramMap;
    }
}

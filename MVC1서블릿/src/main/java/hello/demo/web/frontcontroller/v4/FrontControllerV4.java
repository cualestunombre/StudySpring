package hello.demo.web.frontcontroller.v4;

import hello.demo.web.frontcontroller.ModelView;
import hello.demo.web.frontcontroller.MyView;
import hello.demo.web.frontcontroller.v3.ControllerV3;
import hello.demo.web.frontcontroller.v3.MemberFormControllerV3;
import hello.demo.web.frontcontroller.v3.MemberListControllerV3;
import hello.demo.web.frontcontroller.v3.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="frontControllerV4",urlPatterns = "/front-contoller/v4/*")
public class FrontControllerV4 extends HttpServlet {
    private Map<String, ControllerV4> controllerV4Map = new HashMap<>();

    public FrontControllerV4(){
        controllerV4Map.put("/front-contoller/v4/new-form",new MemberFormControllerV4());
        controllerV4Map.put("/front-contoller/v4/save",new MemberSaveControllerV4());
        controllerV4Map.put("/front-contoller/v4/members",new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String uri = req.getRequestURI();
        ControllerV4 controller = controllerV4Map.get(uri);
        if (controller==null){
            res.setStatus(404);
            return;
        }
        Map<String,String> paramMap = createParamMap(req);
        Map<String ,Object> model = new HashMap<>();


        String viewName = controller.process(paramMap,model);
        MyView view = viewResolver(viewName);
        view.render(model,req,res);
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

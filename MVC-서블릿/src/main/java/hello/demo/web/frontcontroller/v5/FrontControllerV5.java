package hello.demo.web.frontcontroller.v5;

import hello.demo.web.frontcontroller.ModelView;
import hello.demo.web.frontcontroller.MyView;
import hello.demo.web.frontcontroller.v4.ControllerV4;

import hello.demo.web.frontcontroller.v4.MemberFormControllerV4;
import hello.demo.web.frontcontroller.v4.MemberListControllerV4;
import hello.demo.web.frontcontroller.v4.MemberSaveControllerV4;
import hello.demo.web.frontcontroller.v3.MemberFormControllerV3;
import hello.demo.web.frontcontroller.v3.MemberSaveControllerV3;
import hello.demo.web.frontcontroller.v3.MemberListControllerV3;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.*;

@WebServlet(name="forntControllerV5",urlPatterns = "/front-contoller/v5/*")
public class FrontControllerV5 extends HttpServlet {
    private Map<String, Object> controllerV5Map = new HashMap<>();
    private List<MyHandlerAdpater> handlerAdpaters = new ArrayList<>();
    private void initHandlerMappingMap(){
        controllerV5Map.put("/front-contoller/v5/v3/new-form",new MemberFormControllerV3());
        controllerV5Map.put("/front-contoller/v5/v3/save",new MemberSaveControllerV3());
        controllerV5Map.put("/front-contoller/v5/v3/members",new MemberListControllerV3());
        controllerV5Map.put("/front-contoller/v5/v4/new-form",new MemberFormControllerV4());
        controllerV5Map.put("/front-contoller/v5/v4/save",new MemberSaveControllerV4());
        controllerV5Map.put("/front-contoller/v5/v4/members",new MemberListControllerV4());
    }
    private void initHandlerAdapters(){
        handlerAdpaters.add(new ControllerV3HandlerAdapter());
        handlerAdpaters.add(new ControllerV4HandlerAdapter());
    }
    private Object getHandler(HttpServletRequest req){
        String uri = req.getRequestURI();
        return controllerV5Map.get(uri);
    }
    private MyHandlerAdpater getHandlerAdapter(Object handler){
        for(MyHandlerAdpater adapter:handlerAdpaters){
            if (adapter.supports(handler))
                return adapter;
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다");
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        initHandlerAdapters();
        initHandlerMappingMap();
        Object handler = getHandler(req);
        if (handler==null){
            res.setStatus(404);
            return;
        }
        MyHandlerAdpater adapter = getHandlerAdapter(handler);
        ModelView mv = adapter.handle(req,res,handler);
        MyView view = viewResolver(mv.getViewName());
        view.render(mv.getModel(),req,res);
    }
    private MyView viewResolver(String viewName){
        return new MyView("/WEB-INF/views/"+viewName+".jsp");
    }
}

package hello.demo.web.frontcontroller.v5;

import hello.demo.web.frontcontroller.ModelView;
import hello.demo.web.frontcontroller.v4.ControllerV4;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.*;

public class ControllerV4HandlerAdapter implements MyHandlerAdpater{
    @Override
    public boolean supports(Object handler){
        return (handler instanceof ControllerV4);
    }
    @Override
    public ModelView handle(HttpServletRequest req, HttpServletResponse res, Object handler){
        ControllerV4 controller = (ControllerV4) handler;
        Map<String,String> paramMap = createParamMap(req);
        Map<String,Object> model = new HashMap<>();
        String viewName = controller.process(paramMap,model);
        ModelView mv = new ModelView(viewName);
        mv.setModel(model);
        return mv;
    }
    private Map<String, String> createParamMap(HttpServletRequest req){
        Map<String,String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator().forEachRemaining(paramname->paramMap.put(paramname,req.getParameter(paramname)));
        return paramMap;
    }

}

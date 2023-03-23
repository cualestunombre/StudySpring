package hello.demo.web.frontcontroller.v5;

import hello.demo.web.frontcontroller.ModelView;
import hello.demo.web.frontcontroller.v3.ControllerV3;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
public class ControllerV3HandlerAdapter implements MyHandlerAdpater{
    @Override
    public boolean supports(Object handler){
        return (handler instanceof ControllerV3);
    }
    @Override
    public ModelView handle(HttpServletRequest req, HttpServletResponse res, Object handler){
        ControllerV3 controllerV3 = (ControllerV3) handler;
        Map<String,String> paramMap = createParamMap(req);
        ModelView mv = controllerV3.process(paramMap);
        return mv;

    }
    private Map<String, String> createParamMap(HttpServletRequest req){
        Map<String,String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator().forEachRemaining(paramname->paramMap.put(paramname,req.getParameter(paramname)));
        return paramMap;
    }
}

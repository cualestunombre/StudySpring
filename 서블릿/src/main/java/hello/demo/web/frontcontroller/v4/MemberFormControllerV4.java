package hello.demo.web.frontcontroller.v4;

import hello.demo.web.frontcontroller.ModelView;
import hello.demo.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String,String> paramMap, Map<String,Object> model){
        return "new-form";
    }
}

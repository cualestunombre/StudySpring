package hello.demo.web.frontcontroller.v3;
import java.util.*;
import hello.demo.web.frontcontroller.ModelView;
public class MemberFormControllerV3 implements ControllerV3{

    @Override
    public ModelView process(Map<String,String> paramMap){
        return new ModelView("new-form");
    }
}

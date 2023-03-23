package hello.demo.web.frontcontroller.v3;
import hello.demo.web.frontcontroller.ModelView;
import java.util.*;
public interface ControllerV3 {
    ModelView process(Map<String,String> paramMap);
}

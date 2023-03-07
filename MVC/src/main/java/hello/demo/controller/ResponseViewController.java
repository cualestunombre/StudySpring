package hello.demo.controller;

import hello.demo.domain.JsList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class ResponseViewController {
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mav = new ModelAndView("response/hello").addObject("data","hello");
        return mav;
    }
    @GetMapping("/response-view-v2")
    public String responseViewV2(Model model){
        model.addAttribute("data","hello");
        return "response/hello";
    }
    @GetMapping("/response/hello")
    public void responseViewV3(Model model){
        model.addAttribute("data","hello");
        return; //명시성이 너무 떨어져서 사용 권장x
    }
    @ResponseBody
    @GetMapping("/response/Array")
    public JsList responseArray(){
        JsList j = new JsList();
        ArrayList<Object> s = new ArrayList<>();
        HashMap<String, String> h = new HashMap<>();
        h.put("c","d");
        h.put("v","ok");
        s.add("a");
        s.add("v");
        s.add(h);
        j.setS(s);
        return j;
    }

}

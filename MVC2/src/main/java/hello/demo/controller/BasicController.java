package hello.demo.controller;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class BasicController {
    public void addUser(Model model){
        List<Person> list = new ArrayList<>();
        list.add(new Person("seokwoo",26));
        list.add(new Person("minah",44));
        list.add(new Person("homin",19));
        model.addAttribute("list",list);
        return ;
    }
    @Data
    static class Person {
        final private String name;
        final private Integer age;
    }

    @GetMapping("/text-unescaped")
    public String textUnescaped(Model model) {
        model.addAttribute("data", "Hello <b>Spring</b>");
        return "basic/text-unescaped";
    }

    @GetMapping("/variable")
    public String variable(Model model) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("seokwoo", 26));
        people.add(new Person("donggu", 32));
        model.addAttribute("people", people);
        Map<String, Person> peopleMap = new HashMap<>();
        peopleMap.put("seokwoo", new Person("seokwoo", 26));
        model.addAttribute("peopleMap", peopleMap);
        return "basic/variable";

    }

    @GetMapping("/object")
    public String objects(Model model, HttpServletRequest req, HttpServletResponse res, HttpSession session) {
        session.setAttribute("sessionData", "Hello Session");
        model.addAttribute("request", req);
        model.addAttribute("response", res);
        model.addAttribute("servletContext", req.getServletContext());
        return "basic/basic-objects";
    }
    @GetMapping("/link")
    public String link(Model model){
        model.addAttribute("link1","link1");
        model.addAttribute("link2","link2");
        return "basic/link";
    }
    @GetMapping("/literal")
    public String literal(Model model){
        model.addAttribute("data","spring");
        model.addAttribute("null",null);
        return "basic/literal";
    }
    @GetMapping("/attribute")
    public String attribute(){
        return "basic/attribute";
    }
    @GetMapping("/repetition")
    public String repetition(Model model){
        addUser(model);
        return "basic/repetition";
    }
    @GetMapping("/conditional")
    public String conditional(Model model){
        addUser(model);
        return "basic/conditional";
    }
    @GetMapping("/comment")
    public String comment(Model model){
        model.addAttribute("data","spring");
        return "basic/comment";
    }
    @GetMapping("/block")
    public String block(Model model){
        addUser(model);
        return "basic/block";
    }
    @GetMapping("/javascript")
    public String javascript(Model model){
        addUser(model);
        return "basic/javascript";
    }
    @GetMapping("/fragment")
    public String fragment(){
        return "basic/fragment";
    }
    @Component("helloBean")
    static class HelloBean{
        public String hello(String data){
            return "hello "+data;
        }
    }
}
package hello.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class helloController {
    @GetMapping("mvch")
    public String mvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "mvch";
    }
    @GetMapping("apis")
    @ResponseBody
    public Hello helloApi(){
        Hello hello = new Hello();
        hello.setName("sdzxc");
        hello.setValue(12321);
        return hello;
    }
    static class Hello{
        private String name;
        private int value;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getName(){
            return this.name;
        }

        public void setName(String name){
            this.name = name ;
        }
    }
}

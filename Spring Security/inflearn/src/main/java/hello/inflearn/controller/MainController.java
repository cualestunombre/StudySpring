package hello.inflearn.controller;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping("/login")
    public String login(@RequestParam(defaultValue = "false") String error, Model model){

        return "login";
    }

    @GetMapping("/home")
    public String home(@RequestParam(defaultValue = "false") String error,Authentication authentication,Model model){
        model.addAttribute("error",error);
        if(authentication instanceof UsernamePasswordAuthenticationToken){

            model.addAttribute("isLoggedIn",true);
        }
        else{
            model.addAttribute("isLoggedIn",false);
        }

        return "home";
    }
    @GetMapping("/info")
    public String info(){
        return "info";
    }




    @GetMapping("/anonymous")
    public String ano(){return "anonymous";}

    @GetMapping("/hidden/hidden/hidden")
    public String hidden(){
        return "hidden";
    }
    @GetMapping("/user")
    @ResponseBody
    public String user() throws InterruptedException {
        Thread.sleep(1000000000);
        return "this for user";
    }
    @GetMapping("/admin/pay")
    @ResponseBody
    public String admin(){
        return "this for admin";
    }

    @GetMapping("/admin/etc")
    @ResponseBody
    public String adminSYS(){
        return "this for admin and sys";
    }

}

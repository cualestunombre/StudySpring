package hello.demo.controller;

import hello.demo.domain.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@Slf4j
public class ParameterController {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));
        log.info("username={} age={}",username,age);
        res.setContentType("text/plain");
        res.getWriter().write("ok");
    }
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam String username, @RequestParam Integer age) throws IOException{
        log.info("username={} age={}",username,age);
        return "ok";
    }
    // Map으로 파라미터 조회하기
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam Map<String,Object> paramMap){
        log.info("username={} age={}",paramMap.get("username"),paramMap.get("age"));
        return "ok";
    }

    @RequestMapping("/request-param-v4")
    public String requestParamV4(@ModelAttribute HelloData helloData){
        log.info("username={} age={}",helloData.getUsername(),helloData.getAge());
        return "ok";
    }
}

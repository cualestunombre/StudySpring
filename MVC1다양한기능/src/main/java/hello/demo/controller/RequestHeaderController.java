package hello.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    @ResponseBody
    public String headers(HttpServletRequest req,
                          HttpServletResponse res,
                          HttpMethod method,
                          Locale locale,
                          @RequestHeader MultiValueMap<String,String> headerMap,
                          @RequestHeader("host") String host,
                          @CookieValue(name = "SID", required = false) String cookie){
        log.info("request={}",req);
        log.info("response={}",res);
        log.info("httpMethod={}",method);
        log.info("locale={}",locale);
        log.info("headerMap={}",headerMap);
        log.info("header host",host);
        log.info("myCookie={}",cookie);
        return "ok";
    }
}

package hello.demo.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
/*
requestTest는 request타입의 스코프를 테스트 하기위한 클래스임
 */
@Controller
@RequiredArgsConstructor
public class RequestTest {
    private final MyLogger myLogger;

    @ResponseBody
    @RequestMapping("/")
    public String requestTest(HttpServletRequest req){

        myLogger.setUuid(UUID.randomUUID().toString());
        System.out.println(myLogger.getUuid());
        return "hello";
    }


    @Data
    @Component
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    static class MyLogger{
        private String uuid;

    }
}

package hello.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.demo.domain.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest req, HttpServletResponse res) throws IOException{
        ServletInputStream inputStream = req.getInputStream();
        String json = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("json={}",json);
        HelloData helloData = objectMapper.readValue(json,HelloData.class);
        log.info("name={} age={}",helloData.getUsername(),helloData.getAge());
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(json);

    }
    @PostMapping("/request-body-json-v2")
    @ResponseBody
    //단순 plain text를 받기 위함, 사실 plain/text가 아니더라도 파싱가능함
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException{
        HelloData helloData = objectMapper.readValue(messageBody,HelloData.class);
        log.info("name={} age={}",helloData.getUsername(),helloData.getAge());
        return "ok";
    }

    @PostMapping("/request-body-json-v3")
    @ResponseBody
    public String requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException{
        log.info("name={} age={}",helloData.getUsername(),helloData.getAge());
        return "ok";
    }

    @PostMapping("/request-body-json-v5")
    @ResponseBody
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData) throws IOException{
        log.info("name={} age={}",helloData.getUsername(),helloData.getAge());
        return helloData;
    }
    @PostMapping("/request-body-json-v6")
    public HttpEntity<HelloData> requestBodyJsonV6(HttpEntity<HelloData> httpEntity, HttpEntity<String> string) {
        log.info("name={} age={}",httpEntity.getBody().getUsername(),httpEntity.getBody().getAge());
        return new HttpEntity<>(httpEntity.getBody());
    }

    @PostMapping("request-body-json-v7")
    @ResponseBody
    public String requestBodyJsonV7(@ModelAttribute HelloData helloData,HttpEntity<String> ttpData ,HttpServletResponse res){
        log.info("{}",ttpData.getBody());
        log.info("name:{} , age={}",helloData.getUsername(),helloData.getAge());
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        return ttpData.getBody();
    }


}

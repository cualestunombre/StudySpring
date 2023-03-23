package hello.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.demo.domain.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
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
        log.info("name={} age={}",helloData.getName(),helloData.getAge());
        res.getWriter().write(json);

    }
    @PostMapping("/request-body-json-v2")
    @ResponseBody
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException{
        HelloData helloData = objectMapper.readValue(messageBody,HelloData.class);
        log.info("name={} age={}",helloData.getName(),helloData.getAge());
        return "ok";
    }

    @PostMapping("/request-body-json-v3")
    @ResponseBody
    public String requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException{
        log.info("name={} age={}",helloData.getName(),helloData.getAge());
        return "ok";
    }

    @PostMapping("/request-body-json-v5")
    @ResponseBody
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData) throws IOException{
        log.info("name={} age={}",helloData.getName(),helloData.getAge());
        return helloData;
    }


}

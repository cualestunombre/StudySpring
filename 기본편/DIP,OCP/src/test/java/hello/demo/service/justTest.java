package hello.demo.service;
import hello.demo.DemoApplication;

import hello.demo.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import hello.demo.DemoApplication.*;
import java.util.List;
import hello.demo.domain.Hi;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

public class justTest {
    @Test
    void justtest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(hello.demo.DemoApplication.class);
        NetworkService networkService = ac.getBean(NetworkService.class);




    }}


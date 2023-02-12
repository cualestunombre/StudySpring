package hello.demo.service;

import hello.demo.newConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class newSpringTest {
    @Test
    public void springTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(newConfig.class);
        String[] beanNames = ac.getBeanDefinitionNames();
        for(String name : beanNames){
            System.out.println(name);
        }
    }
}

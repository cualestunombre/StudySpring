package hello.demo;
import hello.demo.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;



@SpringBootTest
public class SpringCofigTest {
    @Component
    static class info{
        @Autowired
        public ApplicationContext context;
    }

    @Test
    public void printBeanList() {
        String[] beanNames = new info().context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println(beanName+" ㅋㅋㅋ");
        }
    }
}

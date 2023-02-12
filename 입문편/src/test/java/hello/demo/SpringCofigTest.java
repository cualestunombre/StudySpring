package hello.demo;
import hello.demo.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.assertj.core.api.Assertions.assertThat;
public class SpringCofigTest {

    @Test
    void justTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(hello.demo.DemoApplication.class);
        String[] beanNames = ac.getBeanDefinitionNames();
        for(String name : beanNames){
            System.out.println(name);
        }
    }
}

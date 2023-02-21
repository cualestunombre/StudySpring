package hello.demo.service;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberServiceTest {
    @Test
    public void justTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(hello.demo.DemoApplication.class);
        ClientBean cb1 = ac.getBean(ClientBean.class);
        cb1.check();
        ClientBean cb2 = ac.getBean(ClientBean.class);
        cb2.check();
        if(cb1==cb2){
            System.out.println("ok");
        }


    }

}


package hello.app.proxyvs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
@SpringBootTest
public class BeanTest {
    @Autowired
    A a;

    @Autowired
    B b;
    @Test
    void justTest(){
        a.doWork();
    }

    @TestConfiguration
    static class Conf{
        @Bean
        A a(){
            return new A();
        }
        @Bean
        Alphabet b(){
            return new B();
        }

    }
    static interface Alphabet{
        public void doWork();
    }
    static class A implements Alphabet{
        public void doWork(){
            System.out.println("doWork A");
        }
    }
    static class B implements Alphabet{
        public void doWork(){
            System.out.println("doWork B");
        }
    }
}

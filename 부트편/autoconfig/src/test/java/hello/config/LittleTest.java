package hello.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootTest
public class LittleTest {
    @Autowired
    Greeting greeting;

    @Test
    void littleTest(){
        Assertions.assertThat(greeting).isNotNull();
        System.out.println(((Hello)greeting).name);
    }

    @TestConfiguration
    static class Config{
        @Bean
        Hello hello(){
            return new Hello("a");
        }
        @Bean
        Greeting greeting(){
            return new Hello("b");
        }
    }

    static class Hello implements Greeting{
        public String name;
        public Hello(String name){
            this.name = name;
        }
    }
    static interface Greeting{

    }
}

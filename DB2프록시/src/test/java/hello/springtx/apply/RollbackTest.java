package hello.springtx.apply;

import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class RollbackTest {
    @Autowired
    RollbackService service;

    @Test
    void runtimeException(){
        assertThatThrownBy(()->service.runtimeException()).isInstanceOf(RuntimeException.class);
    }
    @Test
    void checkedException(){
        assertThatThrownBy(()->service.checkedException()).isInstanceOf(MyException.class);
    }
    @Test
    void rollbackFor(){
        assertThatThrownBy(()->service.rollbackFor()).isInstanceOf(MyException.class);
    }
    @TestConfiguration
    static class RollbackTestConfig{
        @Bean
        RollbackService rollbackService(){
            return new RollbackService();
        }
    }
    @Slf4j
    static class RollbackService{
        @Transactional
        public void runtimeException(){//롤백
            log.info("call runtimeException");
            throw new RuntimeException();
        }
        @Transactional
        public void checkedException() throws MyException{//커밋
            log.info("call checkedException");
            throw new MyException();
        }
        @Transactional(rollbackFor = MyException.class) //롤백
        public void rollbackFor() throws MyException{
            log.info("call rollbackFor");
            throw new MyException();
        }

    }
    static class MyException extends Exception{

    }
}

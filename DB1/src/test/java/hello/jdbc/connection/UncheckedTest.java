package hello.jdbc.connection;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
@Slf4j
public class UncheckedTest {
    @Test
    void unchecked_catch(){
        Service service = new Service();
        service.callCatch();
    }
    @Test
    void no_Catch(){
        Service service = new Service();
        Assertions.assertThatThrownBy(()->service.callThrow()).isInstanceOf(MyUnchekedException.class);
    }
    static class MyUnchekedException extends RuntimeException{
        public MyUnchekedException(String message){
            super(message);
        }
    }
    static class Service{
        Repository repository= new Repository();
        public void callCatch(){
            try{
                repository.call();
            }catch(MyUnchekedException e){
                log.info("예외 처리, message={}",e.getMessage(),e);
            }
        }
        public void callThrow(){
            repository.call(); //예외처리 안해도 됨
        }
    }
    static class Repository{
        public void call(){
            throw new MyUnchekedException("ex");
        }
    }
}

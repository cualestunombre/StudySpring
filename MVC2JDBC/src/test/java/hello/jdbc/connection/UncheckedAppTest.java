package hello.jdbc.connection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
@Slf4j
public class UncheckedAppTest {
    @Test
    void unchecked(){
        Controller controller = new Controller();
        assertThatThrownBy(()->controller.request()).isInstanceOf(Exception.class);
    }
    @Test
    void printEx(){
        Controller controller = new Controller();
        try{
            controller.request();
        }catch(Exception e){
            log.info("ex",e);
        }
    } 
    static class Controller {
        Service service = new Service();

        public void request() { //checked exception{
            service.logic();
        }

        static class Service {
            Repository repository = new Repository();
            NetworkClient networkClient = new NetworkClient();

            public void logic() { // exception을 계속 표시해줘야하는 번거로움, OCP위반
                repository.call();
                networkClient.call();
            }

        }

        static class NetworkClient {
            public void call() {
                throw new RuntimeConnectException("연결 실패");
            }
        }

        static class Repository {
            public void call()  {
                try{
                    runSQL();
                }catch (SQLException e){
                    throw  new RuntimeSQLException(e); // 예외에 예외를 담음!
                }
            }
            private void runSQL() throws SQLException{
                throw new SQLException("ex");
            }
        }
    }
    static class RuntimeConnectException extends RuntimeException{
        public RuntimeConnectException(String message){
            super(message);
        }
    }
    static class RuntimeSQLException extends RuntimeException{
        public RuntimeSQLException(){

        }
        public RuntimeSQLException(Throwable cause){
            super(cause);
        }
    }
}

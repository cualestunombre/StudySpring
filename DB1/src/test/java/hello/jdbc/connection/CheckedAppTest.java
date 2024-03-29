package hello.jdbc.connection;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.net.ConnectException;
import java.sql.SQLException;

public class CheckedAppTest {
    @Test
    void checked(){
        Controller controller = new Controller();
        assertThatThrownBy(()->controller.request()).isInstanceOf(SQLException.class);
    }
    static class Controller {
        Service service = new Service();

        public void request() throws SQLException, ConnectException { //checked exception{
            service.logic();
        }

        static class Service {
            Repository repository = new Repository();
            NetworkClient networkClient = new NetworkClient();

            public void logic() throws SQLException, ConnectException { // exception을 계속 표시해줘야하는 번거로움, OCP위반
                repository.call();
                networkClient.call();
            }

        }

        static class NetworkClient {
            public void call() throws ConnectException {
                throw new ConnectException("연결 실패");
            }
        }

        static class Repository {
            public void call() throws SQLException {
                throw new SQLException("ex");
            }
        }
    }
}



package hello.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import static hello.jdbc.connection.ConnectionConst.*;
@Slf4j
public class ConnectionTest {
//    @Test
//    void driverManager() throws SQLException{
//        Connection con1 = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            //커넥션 생성
//        Connection con2 = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            //커넥션 생성
//        System.out.println(con1.toString()+con1.getClass().toString());
//        System.out.println(con2.toString()+con2.getClass().toString());
//    }
//
//    @Test
//    void dataSourceDriverManager() throws SQLException{
//        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL,USERNAME,PASSWORD);
//        useDataSource(dataSource);
//    }
    private void useDataSource(DataSource dataSource) throws SQLException{
        Connection con1 = dataSource.getConnection();
        Connection con2 = dataSource.getConnection();
        System.out.println(con1.toString()+con1.getClass().toString());
        System.out.println(con2.toString()+con2.getClass().toString());
    }
    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException{
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10);
        dataSource.setPoolName("MyPool"); // 데이터 풀을 정의
        useDataSource(dataSource);
        Thread.sleep(1000);

    }
}

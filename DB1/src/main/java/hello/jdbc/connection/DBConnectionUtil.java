package hello.jdbc.connection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;
@Slf4j
public class DBConnectionUtil {
    public static Connection getConnection(){
        try{
            //DriverManager 자체가 DB구현체가 아니라, 등록된 DB드라이버를 관리하는 클래스이다
            Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            log.info("get connection={}, class={}",connection,connection.getClass());
            return connection;
        }catch(SQLException e){
            throw new IllegalStateException(e); // 예외처리가 강제 되지는 않음
        }
    }
}

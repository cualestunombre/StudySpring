package hello.jdbc.connection;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.ex.MyDbException;
import hello.jdbc.repository.ex.MyDuplicateKeyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static  org.springframework.jdbc.support.JdbcUtils.*;
import static hello.jdbc.connection.ConnectionConst.*;
public class ExTranslatorV1Test {
    Repository repository;
    Service service;
    @BeforeEach
    void init(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL,USERNAME,PASSWORD);
        repository = new Repository(dataSource);
        service = new Service(repository);
    }
    @Test
    void duplicateKeySave(){
        service.create("myId");
        service.create("myId");
    }

    @Slf4j
    @RequiredArgsConstructor
    static class Service{
        private final Repository repository;
        public void create(String memberId){
            try{
                repository.save(new Member(memberId,0));
                log.info("saveId={}",memberId);
            }catch(MyDuplicateKeyException e){ //예외를 분리해보고 싶다!!  
                log.info("키 중복!!");
                throw e;
            }catch(MyDbException e){
                log.info("접근 계층 오류",e);
                throw e;
            }
        }
    }
    @RequiredArgsConstructor
    static class Repository{
        private final DataSource dataSource;
        public Member save(Member member){
            String sql = "insert into member(member_id,money) values(?,?)";
            Connection con = null;
            PreparedStatement pstmt = null;
            try{
                con= dataSource.getConnection();
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1,member.getMemberId());
                pstmt.setInt(2,member.getMoney());
                pstmt.executeUpdate();
                return member;
            }catch(SQLException e){
                if(e.getErrorCode()==23505){
                    throw new MyDuplicateKeyException(e);
                }
                throw new MyDbException(e);
            }finally {
                closeStatement(pstmt);
                closeConnection(con);
            }
        }
    }
}

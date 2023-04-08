package hello.jdbc.repository;

import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.NoSuchElementException;


@Component
@Slf4j
public class MemberRepositoryV1 {
    private final DataSource dataSource; // 전달 받은 것
    public MemberRepositoryV1(DataSource dataSource){
        this.dataSource=dataSource;
    }
    public Member save(Member member) throws SQLException{
        String sql ="insert into member(member_id, money) values(?,?)";

        Connection connection = null;
        PreparedStatement pstmt = null;

        try{
            connection=getConnection(); // 커넥션을 전달 받음 -> 직접 커넥션을 뚫는게 아님
            pstmt= connection.prepareStatement(sql);
            pstmt.setString(1,member.getMemberId());
            pstmt.setInt(2,member.getMoney());
            pstmt.executeUpdate();
            return member;
        }catch(SQLException e){
            log.error("db error",e);
            throw e;
        }finally {
            close(connection,pstmt,null);
        }
    }
    public void update(String memberId, int money) throws SQLException{
        String sql = "update member set money=? where member_id=?";

        Connection con = null;
        PreparedStatement pstmt =null;

        try{
            con = getConnection();
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1,money);
            pstmt.setString(2,memberId);
            int resultSize = pstmt.executeUpdate();
            log.info("resultSize={}",resultSize);
        }catch(SQLException e){
            log.error("db error",e);
            throw e;
        }finally {
            close(con,pstmt,null);
        }
    }
    public void delete(String memberId) throws SQLException{
        String sql = "delete from member where member_id=?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,memberId);

            pstmt.executeUpdate();
        }catch(SQLException e){
            log.error("db error",e);
            throw e;
        }
        finally {
            close(con,pstmt,null);
        }
    }
    public Member findById(String memberId) throws SQLException{
        String sql = "select * from member where member_id = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,memberId);

            rs = pstmt.executeQuery(); // 결과를 리턴함

            if(rs.next()){
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            }else{
                throw new NoSuchElementException("memeber not found memberId="+memberId);
            }
        }catch(SQLException e){
            log.error("db error",e);
            throw e;
        }finally {
            close(con,pstmt,rs);
        }
    }

    private void close(Connection con, Statement stmt, ResultSet rs){
        JdbcUtils.closeResultSet(rs); // 자원해제를 제공함(편의 메서드)
        JdbcUtils.closeConnection(con);
        JdbcUtils.closeStatement(stmt);
    }
    private Connection getConnection() throws SQLException{
        Connection con = dataSource.getConnection();
        log.info("get connection={}, class={}",con,con.getClass());
        return con;
    }



}

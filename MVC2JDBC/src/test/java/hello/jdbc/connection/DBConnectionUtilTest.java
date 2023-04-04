package hello.jdbc.connection;

import hello.jdbc.repository.MemberRepositoryV0;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class DBConnectionUtilTest {
    MemberRepositoryV0 repositoryV0 = new MemberRepositoryV0();
    @Test
    void connection(){
        Connection connection = DBConnectionUtil.getConnection();
        assertThat(connection).isNotNull();

    }
    @Test // 회원 저장
    void crud() throws SQLException{
        Member member = new Member("memberV5",10000);
        repositoryV0.save(member);
    }
    @Test //회원 조회
    void search() throws SQLException{
        Member member = repositoryV0.findById("hi2");
        assertThat(member.getMoney()).isEqualTo(20000);
    }
    @Test//수정
    void update() throws SQLException{
        repositoryV0.update("hi2",20000);

    }
    @Test//삭제
    void delete() throws SQLException{
        repositoryV0.delete("memberV2");
    }

}

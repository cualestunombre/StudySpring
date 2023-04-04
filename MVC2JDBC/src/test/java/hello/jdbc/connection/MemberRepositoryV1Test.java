package hello.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV1;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static hello.jdbc.connection.ConnectionConst.*;
@Slf4j
public class MemberRepositoryV1Test {
    MemberRepositoryV1 repositoryV1;
    @BeforeEach
    void beforeEach() throws Exception{
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setJdbcUrl(URL);
        repositoryV1 = new MemberRepositoryV1(dataSource);

    }
    @Test
    void crud() throws SQLException, InterruptedException{
        Member member = new Member("memberV0",100000);
        repositoryV1.save(member);

        Member memberByID = repositoryV1.findById(member.getMemberId());
        Assertions.assertThat(member).isEqualTo(memberByID);

        repositoryV1.update(member.getMemberId(),20000);
        Member updateMember = repositoryV1.findById(member.getMemberId());
        Assertions.assertThat(updateMember.getMoney()).isEqualTo(20000);

        repositoryV1.delete(member.getMemberId());
        Assertions.assertThatThrownBy(()->repositoryV1.findById(member.getMemberId())).isInstanceOf(NoSuchElementException.class);

    }
}

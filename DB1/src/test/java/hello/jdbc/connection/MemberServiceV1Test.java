package hello.jdbc.connection;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV1;
import hello.jdbc.service.MemberServiceV1;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import static  hello.jdbc.connection.ConnectionConst.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MemberServiceV1Test {
    public static final String MEMBER_A ="memberA";
    public static final String MEMBER_B ="memberB";
    public static final String MEMBER_EX ="ex";

    private MemberRepositoryV1 memberRepositoryV1;
    private MemberServiceV1 memberServiceV1;
    @BeforeEach
    void before(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL,USERNAME,PASSWORD);
        memberRepositoryV1 = new MemberRepositoryV1(dataSource);
        memberServiceV1 = new MemberServiceV1(memberRepositoryV1);


    }
    @AfterEach
    void after() throws SQLException{
        memberRepositoryV1.delete(MEMBER_A);
        memberRepositoryV1.delete(MEMBER_B);
        memberRepositoryV1.delete(MEMBER_EX);
    }

    @Test
    @DisplayName("정상 이체")
    void accountTransfer() throws SQLException{
        Member memberA = new Member(MEMBER_A,10000);
        Member memberB = new Member(MEMBER_B,10000);
        memberRepositoryV1.save(memberA);
        memberRepositoryV1.save(memberB);

        memberServiceV1.accountTransfer(memberA.getMemberId(),memberB.getMemberId(),2000);

        Member findMemberA = memberRepositoryV1.findById(memberA.getMemberId());
        Member findMemberB = memberRepositoryV1.findById(memberB.getMemberId());

        Assertions.assertThat(findMemberA.getMoney()).isEqualTo(8000);
        Assertions.assertThat(findMemberB.getMoney()).isEqualTo(12000);

    }

    @Test
    @DisplayName("이체중 예외 발생")
    void accountTransferEx() throws SQLException{
        Member memberA = new Member(MEMBER_A,10000);
        Member memberEx = new Member(MEMBER_EX,10000);
        memberRepositoryV1.save(memberA);
        memberRepositoryV1.save(memberEx);
        Assertions.assertThatThrownBy(()->memberServiceV1.accountTransfer(memberA.getMemberId(),memberEx.getMemberId(),2000)).isInstanceOf(
                IllegalStateException.class
        );
        Member findMemberA = memberRepositoryV1.findById(memberA.getMemberId());
        Member findMemberEx = memberRepositoryV1.findById(memberEx.getMemberId());

        Assertions.assertThat(findMemberA.getMoney()).isEqualTo(8000);
        Assertions.assertThat(findMemberEx.getMoney()).isEqualTo(10000);

    }


}

package hello.jdbc.connection;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import hello.jdbc.service.MemberServiceV3;
import hello.jdbc.service.MemberServiceV3_2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;
public class MemberServiceV3_2Test {
    public static final String MEMBER_A = "memberA";
    public static final String MEMBER_B = "memberB";
    public static final String MEMBER_Ex = "ex";
    private MemberRepositoryV3 memberRepository;
    private MemberServiceV3_2 memberService;

    @BeforeEach
    void before(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL,USERNAME,PASSWORD);
        PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        memberRepository = new MemberRepositoryV3(dataSource);
        memberService = new MemberServiceV3_2(transactionManager,memberRepository);
    }
    @AfterEach
    void after() throws SQLException {

        memberRepository.delete(MEMBER_A);
        memberRepository.delete(MEMBER_B);
        memberRepository.delete(MEMBER_Ex);

    }

    @Test
    @DisplayName("정상 이체")
    void accountTransfer() throws  SQLException{
        Member memberA = new Member(MEMBER_A,10000);
        Member memberB = new Member(MEMBER_B,10000);
        memberRepository.save(memberA);
        memberRepository.save(memberB);
        memberService.accountTransfer(memberA.getMemberId(),memberB.getMemberId(),2000);
        Member findMemberA = memberRepository.findById(memberA.getMemberId());
        Member findMemberB = memberRepository.findById(memberB.getMemberId());


        Assertions.assertThat(findMemberA.getMoney()).isEqualTo(8000);
        Assertions.assertThat(findMemberB.getMoney()).isEqualTo(12000);

    }
    @Test
    @DisplayName("이체 실패")
    void accountTransferRollBack()throws SQLException{
        Member memberA = new Member(MEMBER_A,10000);
        Member memberEx = new Member(MEMBER_Ex,10000);
        memberRepository.save(memberA);
        memberRepository.save(memberEx);
        Assertions.assertThatThrownBy(()->memberService.accountTransfer(memberA.getMemberId(),memberEx.getMemberId(),2000)).isInstanceOf(IllegalStateException.class);
        Member findMemberA = memberRepository.findById(memberA.getMemberId());
        Member findMemberEx = memberRepository.findById(memberEx.getMemberId());
        Assertions.assertThat(findMemberA.getMoney()).isEqualTo(10000);
        Assertions.assertThat(findMemberEx.getMoney()).isEqualTo(10000);

    }
}

package hello.jdbc.connection;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV2;
import hello.jdbc.service.MemberServiceV2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;
public class MemberServiceV2Test {
    private MemberRepositoryV2 memberRepository;
    private MemberServiceV2 memberService;

    @BeforeEach
    void before(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL,USERNAME,PASSWORD);
        memberRepository = new MemberRepositoryV2(dataSource);
        memberService = new MemberServiceV2(dataSource,memberRepository);
    }

    @AfterEach
    void after() throws SQLException{
        memberRepository.delete("memberA");
        memberRepository.delete("memberB");
        memberRepository.delete("ex");
    }


    @Test
    @DisplayName("정상 이체")
    void accountTransfer() throws SQLException{
        Member memberA = new Member("memberA",10000);
        Member memberB = new Member("memberB",10000);
        memberRepository.save(memberA);
        memberRepository.save(memberB);
        memberService.accountTransfer(memberA.getMemberId(),memberB.getMemberId(),2000);
        Member findMemberA = memberRepository.findById("memberA");
        Member findMemberB = memberRepository.findById("memberB");
        Assertions.assertThat(findMemberA.getMoney()).isEqualTo(8000);
        Assertions.assertThat(findMemberB.getMoney()).isEqualTo(12000);
    }

    @Test
    @DisplayName("비정상 이체")
    void accountTransferEx() throws  SQLException{
        Member memberA = new Member("memberA",10000);
        Member memberEx = new Member("ex",10000);
        memberRepository.save(memberA);
        memberRepository.save(memberEx);
        Assertions.assertThatThrownBy(()->memberService.accountTransfer(memberA.getMemberId(), memberEx.getMemberId(), 2000)).isInstanceOf(IllegalStateException.class);
        Member findMemberA = memberRepository.findById(memberA.getMemberId());
        Member findMemberEx = memberRepository.findById(memberEx.getMemberId());
        Assertions.assertThat(findMemberA.getMoney()).isEqualTo(10000);
        Assertions.assertThat(findMemberEx.getMoney()).isEqualTo(10000);
    }


}

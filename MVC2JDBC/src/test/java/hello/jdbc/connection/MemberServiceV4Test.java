package hello.jdbc.connection;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepository;
import hello.jdbc.repository.MemberRepositoryV4_1;
import hello.jdbc.service.MemberServiceV4;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import static org.assertj.core.api.Assertions.*;
import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
@Slf4j
public class MemberServiceV4Test {
    public static final String MEMBER_A = "memberA";
    public static final String MEMBER_B = "memberB";
    public static final String MEMBER_Ex= "ex";

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberServiceV4 memberServiceV4;
    @AfterEach
    void after() throws SQLException{
        memberRepository.delete(MEMBER_A);
        memberRepository.delete(MEMBER_B);
        memberRepository.delete(MEMBER_Ex);
    }
    @TestConfiguration
    static class TestConfig{
        private final DataSource dataSource;
        public TestConfig(DataSource dataSource){
            this.dataSource=dataSource;
        }

        @Bean
        MemberRepository memberRepository(){
            return new MemberRepositoryV4_1(dataSource);
        }
        @Bean
        MemberServiceV4 memberServiceV4(){
            return new MemberServiceV4(memberRepository());
        }
    }
    @Test
    void AopCheck(){
        log.info("member Service class={}",memberServiceV4.getClass());
        log.info("memberRepository class={}",memberRepository.getClass());
        assertThat(AopUtils.isAopProxy(memberServiceV4)).isTrue();
        assertThat(AopUtils.isAopProxy(memberRepository)).isFalse();
    }
    @Test
    @DisplayName("정상 이체")
    void accountTransfer(){
        Member memberA = new Member(MEMBER_A,10000);
        Member memberB =new Member(MEMBER_B,10000);
        memberRepository.save(memberA);
        memberRepository.save(memberB);
        memberServiceV4.accountTransfer(memberA.getMemberId(), memberB.getMemberId(),2000);
        Member findMemberA = memberRepository.findById(memberA.getMemberId());
        Member findMemberB = memberRepository.findById(memberB.getMemberId());
        assertThat(findMemberA.getMoney()).isEqualTo(8000);
        assertThat(findMemberB.getMoney()).isEqualTo(12000);
    }
    @Test
    @DisplayName("이체 중 예외 발생")
    void accountTransferEx(){
        Member memberA = new Member(MEMBER_A,10000);
        Member memberEx = new Member(MEMBER_Ex,10000);
        memberRepository.save(memberA);
        memberRepository.save(memberEx);
        assertThatThrownBy(()->{memberServiceV4.accountTransfer(memberA.getMemberId(),memberEx.getMemberId(),2000);}).isInstanceOf(IllegalStateException.class);
        Member findMemberA = memberRepository.findById(memberA.getMemberId());
        Member findMemberEx = memberRepository.findById(memberEx.getMemberId());
        assertThat(findMemberA.getMoney()).isEqualTo(10000);
        assertThat(findMemberEx.getMoney()).isEqualTo(10000);
    }

}

package hello.demo.service;

import hello.demo.domain.Member;
import hello.demo.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



@SpringBootTest //스프링 부트 컨테이이너와 테스트를 진행함
@Transactional //DB를 다시 롤백 시킴
public class MemberServiceIntegrationTest {
    @Autowired
    MemberServiceImpl memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입(){
        Member member = new Member();
        member.setName("hello");

        Long saveId = memberService.join(member);

        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(),findMember.getName());

    }

    @Test
    public void 중복_회원_예외(){
        Member member1= new Member();
        member1.setName("s");
        Member member2 = new Member();
        member2.setName("s");

        memberService.join(member1);
        IllegalStateException e =assertThrows(IllegalStateException.class,()->memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
    }

}

package hello.demo.service;
import hello.demo.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class justTest {
    @Test
    void justtest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(hello.demo.DemoApplication.class);
        MemberService memberService = ac.getBean(MemberService.class);
        memberService.join(new Member(12));
        memberService.join(new Member(13));
        List<Member> members = memberService.findMembers();
        MemberSelection memberSelection = ac.getBean(MemberSelection.class);
        memberSelection.select();
        for(Member member:members){
            System.out.println(member.getLanguage());
        }



    }
}

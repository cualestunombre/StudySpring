package hello.demo.service;

import hello.demo.AppConfig;
import hello.demo.domain.Member;
import hello.demo.repository.MemberRepository;
import hello.demo.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class MemberServiceTest {
    @Test
    public void test(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(hello.demo.DemoApplication.class);
        MemberService m = ac.getBean(MemberServiceImpl.class);
        m.join(new Member());
        MemberRepository m2 = ac.getBean(MemberRepository.class);
        System.out.println(m.hashCode());
        System.out.println(m2.hashCode());
    }


}

package hello.app.proxyvs;

import hello.app.member.MemberService;
import hello.app.member.MemberServiceImpl;
import hello.app.proxyvs.code.ProxyDIAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"})
@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"}) //CGLIB 프록시
@Import(ProxyDIAspect.class)
public class ProxyDITest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberServiceImpl memberServiceImpl;
    //주입 자체가 안되지 않을까?

    @Test
    void go(){
        log.info("memberService class={}",memberService.getClass());
        log.info("memberServiceImpl class={}",memberServiceImpl.getClass());
    }



}

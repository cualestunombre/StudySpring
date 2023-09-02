package hello.app.proxyvs;

import hello.app.member.MemberService;
import hello.app.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class ProxyCastingTest {
    @Test
    void jdkProxy(){
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false);
        //JDK 동적 프록시

        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        assertThatThrownBy(()->{MemberServiceImpl memberServiceImpl = (MemberServiceImpl) memberServiceProxy;})
                .isInstanceOf(ClassCastException.class);

    }

    @Test
    void cglibProxy(){
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true);

        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        MemberServiceImpl memberServiceImpl = (MemberServiceImpl) memberServiceProxy;
    }
}

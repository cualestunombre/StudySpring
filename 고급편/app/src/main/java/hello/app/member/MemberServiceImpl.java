package hello.app.member;

import hello.app.member.annotation.ClassAop;
import hello.app.member.annotation.MethodAop;
import org.springframework.stereotype.Component;

import java.lang.reflect.Member;
@ClassAop
@Component
public class MemberServiceImpl implements MemberService {
    @Override
    @MethodAop("test value")
    public String hello(String param){
        return "ok";
    }

    public String internal(String param){
        return "ok";
    }
}

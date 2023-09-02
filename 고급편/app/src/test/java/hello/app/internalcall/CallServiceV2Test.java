package hello.app.internalcall;

import hello.app.internalcall.aop.CallLogAspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(CallLogAspect.class)
@SpringBootTest
public class CallServiceV2Test {
    @Autowired
    CallServiceV2 callServiceV2;

    @Test
    void external(){
        callServiceV2.external();
    }
}

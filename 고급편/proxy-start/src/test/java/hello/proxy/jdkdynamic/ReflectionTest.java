package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {
    @Test
    void reflection0(){
        Hello hello = new Hello();
        hello.callA();
        hello.callB();
    }
    @Slf4j
    static class Hello{
        public String callA(){
            log.info("callA");
            return "A";
        }
        public String callB(){
            log.info("callB");
            return "B";
        }
    }
    @Test
    void reflection1() throws Exception{
        Hello target = new Hello();
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
        Method methodCallA = classHello.getMethod("callA");
        Object result1 = methodCallA.invoke(target);
        log.info("result={}",result1);

        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(target);
        log.info("result2={}",result2);

    }
    @Test
    void reflection2() throws Exception{
        Hello target = new Hello();
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
        Method methodA = classHello.getMethod("callA");
        dynamicCall(methodA,target);

        Method methodB = classHello.getMethod("callB");
        dynamicCall(methodB,target);


    }

    private void dynamicCall(Method method, Object target) throws Exception{
        log.info("start");
        Object result = method.invoke(target);
        log.info("result={}",result);
    }
}

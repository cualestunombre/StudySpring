package hello.app.exam.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;

@Slf4j
@Aspect
@Order(value = 2)
public class TraceAspect {
    @Before("@annotation(hello.app.exam.annotation.Trace)")
    public void doTrace(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        log.info("[trace] {} args {}",joinPoint.getSignature(),args);
    }
}

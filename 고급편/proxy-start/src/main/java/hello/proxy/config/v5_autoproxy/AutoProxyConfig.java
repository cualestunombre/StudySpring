package hello.proxy.config.v5_autoproxy;

import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoProxyConfig {
//    @Bean
//    public Advisor advisor1(LogTrace logTrace){
//        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
//        pointcut.setMappedNames("order*","save*");
//        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
//
//        return new DefaultPointcutAdvisor(pointcut,advice);
//    }
//    @Bean
//    public Advisor advisor2(LogTrace logTrace){
//        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
//        pointcut.setMappedNames("request*");
//        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
//
//        return new DefaultPointcutAdvisor(pointcut,advice);
//    }
//    @Bean
//    public Advisor advisor2(LogTrace logTrace){
//        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
//        pointcut.setExpression("execution(* hello.proxy.app..*(..)) && !execution(* hello.proxy.app..noLog(..))");
//        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
//
//        return new DefaultPointcutAdvisor(pointcut, advice);
//    }
}

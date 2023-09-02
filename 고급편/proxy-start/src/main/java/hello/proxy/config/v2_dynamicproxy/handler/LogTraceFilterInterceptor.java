package hello.proxy.config.v2_dynamicproxy.handler;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.util.PatternMatchUtils;

import java.lang.reflect.Method;
@RequiredArgsConstructor
@Slf4j
public class LogTraceFilterInterceptor implements MethodInterceptor {
    private final Object target;
    private final LogTrace logTrace;
    private final String[] patterns;

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable{
        String mehtodName = method.getName();
        if(!PatternMatchUtils.simpleMatch(patterns,mehtodName)){
            return method.invoke(target,args);
        }
        TraceStatus status = null;
        try{
            String message = method.getDeclaringClass().getSimpleName() +"."
                    + method.getName() +"()";
            status = logTrace.begin(message);

            Object result = method.invoke(target,args);

            logTrace.end(status);
            return result;

        }catch (Exception e){
            logTrace.exception(status,e);
            throw e;
        }
    }
}

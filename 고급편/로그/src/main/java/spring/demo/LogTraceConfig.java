package spring.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.demo.trace.logtrace.FieldLogTrace;
import spring.demo.trace.logtrace.LogTrace;
import spring.demo.trace.logtrace.ThreadLocalLogTrace;

@Configuration
public class LogTraceConfig {
    @Bean
    public LogTrace logTrace(){
        return new ThreadLocalLogTrace();
    }
}

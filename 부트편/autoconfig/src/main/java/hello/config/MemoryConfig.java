package hello.config;

import memory.MemoryCondition;
import memory.MemoryController;
import memory.MemoryFinder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
//@Conditional(MemoryCondition.class) #클래스로 설정하는 방법
@ConditionalOnProperty(name="memory",havingValue = "on")
public class MemoryConfig {
    @Bean
    public MemoryController memoryController(){
        return new MemoryController(memoryFinder());
    }
    @Bean
    public MemoryFinder memoryFinder(){
        return new MemoryFinder();
    }
}

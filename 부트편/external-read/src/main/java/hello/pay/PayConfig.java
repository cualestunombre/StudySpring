package hello.pay;

import hello.pay.LocalPayClient;
import hello.pay.ProdPayClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
public class PayConfig {
    @Bean
    @Profile("default")
    public LocalPayClient localPayClient(){
        log.info("LocalPayClient 빈 등록");
        return new LocalPayClient();
    }
    @Bean
    @Profile("prod")
    public ProdPayClient prodPayClient(){
        log.info("ProdPayClient 빈 등록");
        return new ProdPayClient();
    }

}

package hello.proxy;

import hello.proxy.config.CglibProxyConfig;

import hello.proxy.config.InterfaceProxyConfig;
import hello.proxy.config.DynamicProxyBasicConfig;
import hello.proxy.config.ProxyFactoryConfigV1;
import hello.proxy.config.v4_postprocessor.postprocessor.BeanPostProcessorConfig;
import hello.proxy.config.v5_autoproxy.AutoProxyConfig;
import hello.proxy.config.v6_aop.AopConfig;
import hello.proxy.trace.logtrace.LogTrace;
import hello.proxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({AopConfig.class})
@SpringBootApplication(scanBasePackages = "hello.proxy.app") // 오토 스캔 + configuration 수용 범위
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

	@Bean
	public LogTrace logTrace(){
		return new ThreadLocalLogTrace();
	}


}

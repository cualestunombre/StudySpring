package hello.proxy.config;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceFilterInterceptor;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CglibProxyConfig {
    private static final String[] PATTERNS = {"request*","order*","save*"};

    @Bean
    OrderControllerV2 orderController(LogTrace logTrace){
        OrderControllerV2 target = new OrderControllerV2();
        target.setOrderService(orderService(logTrace));

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(OrderControllerV2.class);
        enhancer.setCallback(new LogTraceFilterInterceptor(target,logTrace,PATTERNS));
        OrderControllerV2 proxy = (OrderControllerV2) enhancer.create();
        return proxy;

    }
    @Bean
    OrderServiceV2 orderService(LogTrace logTrace){

        OrderServiceV2 target = new OrderServiceV2();
        target.setOrderRepository(orderRepository(logTrace));

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(OrderServiceV2.class);
        enhancer.setCallback(new LogTraceFilterInterceptor(target,logTrace,PATTERNS));
        OrderServiceV2 proxy = (OrderServiceV2) enhancer.create();
        return proxy;

    }
    @Bean
    OrderRepositoryV2 orderRepository(LogTrace logTrace){
        OrderRepositoryV2 target = new OrderRepositoryV2();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(OrderRepositoryV2.class);
        enhancer.setCallback(new LogTraceFilterInterceptor(target,logTrace,PATTERNS));
        OrderRepositoryV2 proxy = (OrderRepositoryV2) enhancer.create();
        return proxy;
    }


}

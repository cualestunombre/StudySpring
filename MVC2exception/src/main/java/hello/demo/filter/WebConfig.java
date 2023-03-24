package hello.demo.filter;

import hello.demo.interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
@Configuration
public class WebConfig implements WebMvcConfigurer {
//    @Autowired
//    private LogFilter logFilter;
//    @Bean
//    public FilterRegistrationBean logFilterReg(){
//        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
//        filterRegistrationBean.setFilter(logFilter);
//        filterRegistrationBean.setOrder(1);
//        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST);
//        return filterRegistrationBean;
//
//    }
    @Autowired
    private LogInterceptor logInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(logInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/css/**","/*.ico",
                        "/error","error-page/**"
                        );
    }
}

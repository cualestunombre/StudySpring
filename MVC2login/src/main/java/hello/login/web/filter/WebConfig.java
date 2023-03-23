package hello.login.web.filter;

import hello.login.web.argumentresolver.LoginMemberArgumentResolver;
import hello.login.web.interceptor.LogIntercepter;
import hello.login.web.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LogFilter filter;
    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;
    @Autowired
    private LogIntercepter logIntercepter;
//    @Bean
//    public FilterRegistrationBean logFilter(){
//        FilterRegistrationBean<Filter>  filterFilterRegistrationBean = new FilterRegistrationBean<>();
//        filterFilterRegistrationBean.setFilter(filter); //Bean에서 주입되는 것이 아님
//        filterFilterRegistrationBean.setOrder(1);
//        filterFilterRegistrationBean.addUrlPatterns("/*");
//        return filterFilterRegistrationBean;
//    }
//    @Bean
//    public FilterRegistrationBean loginCheckFilter(){
//        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
//        filterFilterRegistrationBean.setFilter(new LoginCheckFilter());
//        filterFilterRegistrationBean.setOrder(2);
//        filterFilterRegistrationBean.addUrlPatterns("/*");
//        return filterFilterRegistrationBean;
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(logIntercepter)
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**","/*.ico","/error");
        registry.addInterceptor(loginCheckInterceptor)
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/","/members/add","/login","/logout","/css/**","/*.ico","/error");
    }
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers){
        resolvers.add(new LoginMemberArgumentResolver());
    }
}

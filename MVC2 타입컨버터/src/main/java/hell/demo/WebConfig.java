package hell.demo;

import hell.demo.converter.MyNumberFormatter;
import hell.demo.converter.SockAddressToStringConverter;
import hell.demo.converter.StringToSockAddressConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry){
        registry.addConverter(new SockAddressToStringConverter());
        registry.addConverter(new StringToSockAddressConverter());
        registry.addFormatter(new MyNumberFormatter());
    }
}

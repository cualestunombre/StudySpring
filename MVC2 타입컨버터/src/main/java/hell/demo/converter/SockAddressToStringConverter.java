package hell.demo.converter;

import hell.demo.domain.SockAddress;
import org.springframework.core.convert.converter.Converter;
import org.thymeleaf.util.StringUtils;

public class SockAddressToStringConverter implements Converter<SockAddress, String> {
    @Override
    public String convert(SockAddress source) {
        return source.getIp()+":"+ StringUtils.toString(source.getPort());
    }
}

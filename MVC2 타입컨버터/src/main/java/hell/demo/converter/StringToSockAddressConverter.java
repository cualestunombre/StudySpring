package hell.demo.converter;

import hell.demo.domain.SockAddress;
import org.springframework.core.convert.converter.Converter;


public class StringToSockAddressConverter implements Converter<String, SockAddress> {
    @Override
    public SockAddress convert(String source){
        String[] split = source.split(":");
        String ip = split[0];
        int port = Integer.parseInt(split[1]);
        return new SockAddress(split[0],port);
    }


}

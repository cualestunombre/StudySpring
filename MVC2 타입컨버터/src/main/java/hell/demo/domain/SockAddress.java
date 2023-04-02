package hell.demo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@EqualsAndHashCode
public class SockAddress{
    private String ip;
    private int port;

    public SockAddress(String ip,int port){
        this.ip=ip;
        this.port=port;
    }

}

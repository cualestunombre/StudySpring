package hello.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AImpl implements AInterface{
    @Override
    public String call(){
        log.info("A 호출");
        return "A";
    }
    @Override
    public String play(){
        log.info("볼레로 재생");
        return "볼레로";
    }
}

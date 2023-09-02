package hello.app.internalcall;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CallServiceV2 {
    private final ObjectProvider<CallServiceV2> callServiceV2ObjectProvider;

    public void external(){
        log.info("call external");
        CallServiceV2 callServiceV2 = callServiceV2ObjectProvider.getObject();
        callServiceV2.internal();
    }

    public void internal(){
        log.info("call internal");
    }
}

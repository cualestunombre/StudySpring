package spring.demo.app.v3;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.demo.trace.TraceStatus;
import spring.demo.trace.hellotrace.HelloTraceV2;
import spring.demo.trace.logtrace.LogTrace;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {
    private final OrderServiceV3 orderService;
    private final LogTrace trace;

    @GetMapping("/v3/request")
    public Json request(String itemId){
        TraceStatus status = null;
        try{
            status = trace.begin("OrderController.request()");
            orderService.orderItem(itemId);
            trace.end(status);
        }catch (Exception e){
            trace.exception(status,e);
            throw e;
        }

        Json res = new Json();
        res.setAnswer(itemId);
        return res;
    }
    @Getter
    @Setter
    static class Json{
        String answer = "ok";
    }
}

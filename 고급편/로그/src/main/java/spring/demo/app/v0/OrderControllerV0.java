package spring.demo.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.demo.trace.TraceStatus;
import spring.demo.trace.hellotrace.HelloTraceV1;

@RestController
@RequiredArgsConstructor
public class OrderControllerV0 {
    private final OrderServiceV0 orderService;
    private final HelloTraceV1 trace;

    @GetMapping("/v0/request")
    public String request(String itemId){
        TraceStatus status = null;
        try{
            status = trace.begin("OrderController.request()");
            orderService.orderItem(itemId);
            trace.end(status);
        }catch (Exception e){
            trace.exception(status,e);
            throw e;
        }

        return "ok";
    }
}

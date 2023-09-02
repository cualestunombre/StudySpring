package spring.demo.app.v5;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.demo.trace.callback.TraceTemplate;
import spring.demo.trace.logtrace.LogTrace;
import spring.demo.trace.template.AbstractTemplate;

@RestController
@RequiredArgsConstructor
public class OrderControllerV5 {
    private final OrderServiceV5 orderService;
    private final LogTrace trace;

    @GetMapping("/v5/request")
    public Json request(String itemId){
        TraceTemplate template = new TraceTemplate(trace);
        Json res = new Json();
        res.setAnswer(template.execute("OrderController.request()",()->{orderService.orderItem(itemId);return itemId;}));
        return res;
    }
    @Getter
    @Setter
    static class Json{
        String answer = "ok";
    }
}

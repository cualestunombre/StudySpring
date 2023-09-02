package spring.demo.app.v4;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.demo.trace.TraceStatus;
import spring.demo.trace.logtrace.LogTrace;
import spring.demo.trace.template.AbstractTemplate;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {
    private final OrderServiceV4 orderService;
    private final LogTrace trace;

    @GetMapping("/v4/request")
    public Json request(String itemId){
        AbstractTemplate<String> template = new AbstractTemplate<String>(trace) {
            @Override
            protected String call() {
                orderService.orderItem(itemId);
                return itemId;
            }
        };

        Json res = new Json();
        res.setAnswer(template.execute("OrderController.request()"));
        return res;
    }
    @Getter
    @Setter
    static class Json{
        String answer = "ok";
    }
}

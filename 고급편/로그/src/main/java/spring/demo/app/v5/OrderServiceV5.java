package spring.demo.app.v5;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.demo.trace.callback.TraceTemplate;
import spring.demo.trace.logtrace.LogTrace;
import spring.demo.trace.template.AbstractTemplate;

@Service
@RequiredArgsConstructor
public class OrderServiceV5 {
    private final OrderRepositoryV5 orderRepository;
    private final LogTrace trace;
    public void orderItem(String itemId){
        TraceTemplate template = new TraceTemplate(trace);
        template.execute("OrderService.orderItem()",()->{orderRepository.save(itemId); return null;});

    }


}

package spring.demo.app.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.demo.trace.TraceStatus;
import spring.demo.trace.logtrace.LogTrace;
import spring.demo.trace.template.AbstractTemplate;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {
    private final OrderRepositoryV4 orderRepository;
    private final LogTrace trace;
    public void orderItem(String itemId){
        AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {
            @Override
            protected Void call() {
                orderRepository.save(itemId);
                return null;
            }
        };
        template.execute("OrderService.orderItem()");

    }


}

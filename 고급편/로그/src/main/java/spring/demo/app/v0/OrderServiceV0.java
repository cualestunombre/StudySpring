package spring.demo.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.demo.trace.TraceStatus;
import spring.demo.trace.hellotrace.HelloTraceV1;

@Service
@RequiredArgsConstructor
public class OrderServiceV0 {
    private final OrderRepositoryV0 orderRepository;
    private final HelloTraceV1 trace;
    public void orderItem(String itemId){
        TraceStatus status = null;
        try{
            status = trace.begin("OrderService.orderItem()");
            orderRepository.save(itemId);
            trace.end(status);
        }catch (Exception e){
            trace.exception(status,e);
            throw e;
        }

    }


}

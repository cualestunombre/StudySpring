package spring.demo.app.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.demo.trace.TraceId;
import spring.demo.trace.TraceStatus;
import spring.demo.trace.hellotrace.HelloTraceV2;
import spring.demo.trace.logtrace.LogTrace;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {
    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;
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

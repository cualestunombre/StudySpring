package spring.demo.app.v5;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.demo.trace.callback.TraceTemplate;
import spring.demo.trace.logtrace.LogTrace;
import spring.demo.trace.template.AbstractTemplate;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV5 {
    private final LogTrace trace;
    public void save(String itemId){
        TraceTemplate template = new TraceTemplate(trace);
        template.execute("OrderRepository.save()",()->{
            if (itemId.equals("ex")){
            throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);
            return null;});



    }

    private void sleep(int millis){
        try{
            Thread.sleep(millis);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

package spring.demo.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.demo.trace.TraceStatus;
import spring.demo.trace.hellotrace.HelloTraceV1;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {
    private final HelloTraceV1 trace;
    public void save(String itemId){

        TraceStatus status = null;
        try{
            status = trace.begin("OrderRepository.save()");
            if(itemId.equals("ex")){
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);

            trace.end(status);
        }catch (Exception e){
            trace.exception(status,e);
            throw e;
        }


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

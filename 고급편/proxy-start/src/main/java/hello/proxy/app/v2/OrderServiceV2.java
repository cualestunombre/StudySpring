package hello.proxy.app.v2;

import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.app.v1.OrderServiceV1;
import lombok.Setter;

@Setter
public class OrderServiceV2 {
    private OrderRepositoryV2 orderRepository;

    public OrderServiceV2(){}


    public void orderItem(String itemId){
        orderRepository.save(itemId);
    }
}

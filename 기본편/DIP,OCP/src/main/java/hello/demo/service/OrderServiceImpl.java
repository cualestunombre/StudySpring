package hello.demo.service;
import hello.demo.domain.Member;

public class OrderServiceImpl implements OrderService {
    private DiscountPolicy discountPolicy;
    public double createOrder(Member member, double price){
        if(member.isVip()){
            return price- discountPolicy.getValue(price);
        }
        else{
            return price;
        }
    }
}

package hello.demo.service;

import hello.demo.domain.Member;

public interface OrderService {
    public double createOrder(Member member, double price);
}

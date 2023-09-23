package hello.core;

import hello.core.item.Item;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {

        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Member member = new Member(1L, "member1", Grade.VIP);
        Item item = new Item("item1", 10000);

        // 회원 가입
        memberService.join(member);

        // 상품 주문
        Order order = orderService.createOrder(member.getId(), item);
        System.out.println(order.toString());

    }
}

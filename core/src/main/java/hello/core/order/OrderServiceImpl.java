package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.item.Item;
import hello.core.member.*;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemberRepositoryImpl();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, Item item) {
        Member member = memberRepository.findById(memberId);
        return new Order(memberId, item.getItemName(), item.getItemPrice(), discountPolicy.discount(member, item.getItemPrice()));
        // fix disxount 정책의 경우 itemPrice와 상관없이 grade 따라 할인 정책 차등 적용
    }
}

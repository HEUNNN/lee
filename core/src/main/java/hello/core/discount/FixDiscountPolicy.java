package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (Grade.VIP == member.getGrade()) { // Enum은 == 으로 비교해야 함
            return discountFixAmount;
        }
        return 0;
    }
}

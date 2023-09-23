package hello.core.order;

import hello.core.item.Item;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderServiceImplTest {

    private MemberService memberService = new MemberServiceImpl();
    private OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrderTest() {

        //given
        Long memberId = 1L;
        Member member = new Member(memberId, "member1", Grade.VIP);
        Item item = new Item("item A", 10000);

        memberService.join(member);

        // when
        Order order = orderService.createOrder(memberId, item);

        // then
        Assertions.assertThat(order.getMemberId()).isEqualTo(memberId);
        Assertions.assertThat(order.getItemName()).isEqualTo(item.getItemName());
        Assertions.assertThat(order.getItemPrice()).isEqualTo(item.getItemPrice());
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}
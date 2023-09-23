package hello.core.order;

import hello.core.item.Item;

public interface OrderService {

    Order createOrder(Long memberId, Item item);
}

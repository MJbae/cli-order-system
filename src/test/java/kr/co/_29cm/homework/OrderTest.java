package kr.co._29cm.homework;

import kr.co._29cm.homework.domain.Item;
import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.util.List;

@Transactional
class OrderTest {
    private OrderService orderService;
    private OrderItemService orderItemService;

    private final OrderRepository orderRepository = mock(OrderRepository.class);
    private final OrderItemRepository orderItemRepository = mock(OrderItemRepository.class);
    private final ItemService itemService = mock(ItemService.class);
    private final Order orderMocking = mock(Order.class);
    private Item item;
    private OrderItem orderItem;
    private final int orderCount = 1;
    private final Long itemId = 778422L;
    private final String itemName = "캠핑덕 우드롤테이블";
    private final int stockQuantity = 7;
    private final BigDecimal itemPrice = BigDecimal.valueOf(45000);
    private final BigDecimal deliveryFee = BigDecimal.valueOf(2500);

    @BeforeEach
    void setUp() {
        orderService = new OrderService(orderRepository, orderItemRepository, itemService);
        orderItemService = new OrderItemService(orderItemRepository);
        item = new Item(itemId, itemName, itemPrice, stockQuantity);
        orderItem = new OrderItem(orderMocking, item, orderCount);

        given(itemService.loadOne(any())).willReturn(item);
        given(orderItemRepository.findByOrder(any())).willReturn(List.of());

    }

    @Test
    public void 주문금액이_5만원_이상이면_지불금액에_배송료가_붙지않는다() {
        Order order = orderService.order(itemId, orderCount);

        BigDecimal actualPrice = order.getPrice();
        BigDecimal expectedPrice = itemPrice.multiply(BigDecimal.valueOf(orderCount));

        assertThat(actualPrice).isEqualTo(expectedPrice);
    }

    @Test
    public void 주문금액이_5만원_미만이면_지불금액에_배송료가_붙는다() {
        Order order = orderService.order(itemId, orderCount);

        BigDecimal actualPrice = order.getPrice();
        BigDecimal expectedPrice = itemPrice.multiply(BigDecimal.valueOf(orderCount * 2)).add(deliveryFee);

        assertThat(actualPrice).isEqualTo(expectedPrice);
    }
}
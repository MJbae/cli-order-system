package kr.co._29cm.homework.application;

import kr.co._29cm.homework.infra.OrderItemRepository;
import kr.co._29cm.homework.infra.OrderRepository;
import kr.co._29cm.homework.domain.Item;
import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.OrderItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class OrderService {
    private final OrderRepository repository;
    private final OrderItemRepository orderItemRepository;
    private final ItemService itemService;

    public OrderService(OrderRepository repository, OrderItemRepository orderItemRepository, ItemService itemService) {
        this.repository = repository;
        this.orderItemRepository = orderItemRepository;
        this.itemService = itemService;
    }

    public Order order(Long itemId, int count) {
        Order order = new Order();
        Item itemOrdered = itemService.loadOne(itemId);
        OrderItem orderItem = new OrderItem(order, itemOrdered, count);
        // TODO: 부동 소수점을 고려한 연산 로직으로 변경
        BigDecimal totalPrice = BigDecimal.valueOf(itemOrdered.getPrice().longValue() * orderItem.getCount());
        order.markPrice(totalPrice);
        
        repository.save(order);
        orderItemRepository.save(orderItem);

        return order;
    }


    public List<OrderItem> loadOrderItems(Order order){
        return order.getOrderItems();
    }
}

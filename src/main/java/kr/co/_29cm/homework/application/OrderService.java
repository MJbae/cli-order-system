package kr.co._29cm.homework.application;

import kr.co._29cm.homework.cli.OrderDto;
import kr.co._29cm.homework.exception.SoldOutException;
import kr.co._29cm.homework.infra.OrderItemRepository;
import kr.co._29cm.homework.infra.OrderRepository;
import kr.co._29cm.homework.domain.Item;
import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.OrderItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    public Order order(List<OrderDto> orderDtos) {
        Order order = new Order();
        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalPrice = new BigDecimal(0);

        for (OrderDto orderDto : orderDtos) {
            Item itemOrdered = itemService.loadOne(orderDto.getItemId());
            int itemCountOrdering = orderDto.getItemCount();
            int itemStockQuantity = itemOrdered.getStockQuantity();

            if (itemCountOrdering > itemStockQuantity) {
                throw new SoldOutException();
            }

            itemOrdered.decreaseStock(itemCountOrdering);

            OrderItem orderItem = new OrderItem(order, itemOrdered, itemCountOrdering);
            orderItems.add(orderItem);

            // TODO: 부동 소수점을 고려한 연산 로직으로 변경
            BigDecimal priceSum = BigDecimal.valueOf(itemOrdered.getPrice().longValue() * orderItem.getCount());
            totalPrice = totalPrice.add(priceSum);

            itemService.save(itemOrdered);
        }

        if (totalPrice.longValue() < 50000L) {
            order.markPrice(totalPrice.add(new BigDecimal(2500)));
        } else {
            order.markPrice(totalPrice);
        }

        repository.save(order);
        for (OrderItem orderItem : orderItems) {
            orderItemRepository.save(orderItem);
        }

        return order;
    }

    public List<OrderItem> loadOrderItems(Order order) {
        return order.getOrderItems();
    }
}

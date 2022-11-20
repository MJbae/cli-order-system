package kr.co._29cm.homework.application;

import kr.co._29cm.homework.infra.OrderItemRepository;
import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.OrderItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> loadOneBy(Order order){
        return orderItemRepository.findByOrder(order);
    }

    @Transactional
    public OrderItem save(OrderItem orderItem){
        return orderItemRepository.save(orderItem);
    }
}

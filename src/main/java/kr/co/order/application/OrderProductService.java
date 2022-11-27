package kr.co.order.application;

import kr.co.order.application.interfaces.OrderItemService;
import kr.co.order.infra.OrderItemRepository;
import kr.co.order.domain.Order;
import kr.co.order.domain.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderProductService implements OrderItemService<OrderItem, Order> {
    private final OrderItemRepository repository;

    public List<OrderItem> loadOneBy(Order order) {
        return repository.findByOrder(order);
    }

    @Transactional
    public void save(OrderItem orderItem) {
        repository.save(orderItem);
    }
}

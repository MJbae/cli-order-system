package kr.co._29cm.homework.application;

import kr.co._29cm.homework.infra.OrderItemRepository;
import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.OrderItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderProductService {
    private final OrderItemRepository repository;

    public OrderProductService(OrderItemRepository orderItemRepository) {
        this.repository = orderItemRepository;
    }

    public List<OrderItem> loadOneBy(Order order) {
        return repository.findByOrder(order);
    }

    @Transactional
    public void save(OrderItem orderItem) {
        repository.save(orderItem);
    }
}

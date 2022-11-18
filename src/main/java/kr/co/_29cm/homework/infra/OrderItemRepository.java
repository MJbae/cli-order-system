package kr.co._29cm.homework.infra;

import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder(Order order);
}

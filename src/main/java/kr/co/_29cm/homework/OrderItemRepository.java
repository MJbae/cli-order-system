package kr.co._29cm.homework;

import kr.co._29cm.homework.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}

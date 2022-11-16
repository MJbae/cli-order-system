package kr.co._29cm.homework;

import kr.co._29cm.homework.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

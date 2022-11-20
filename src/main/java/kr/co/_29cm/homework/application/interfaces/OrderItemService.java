package kr.co._29cm.homework.application.interfaces;

import kr.co._29cm.homework.application.OrderProductService;
import kr.co._29cm.homework.application.ProductService;
import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.OrderItem;

import java.util.List;

/**
 * 주문상품에 대한 비니지스 로직을 처리한다
 * <p>
 * All Known Implementing Classes:
 * @see OrderProductService
 * </p>
 */
public interface OrderItemService<T extends OrderItem, S extends Order> {
    List<T> loadOneBy(S order);

    void save(T orderItem);
}

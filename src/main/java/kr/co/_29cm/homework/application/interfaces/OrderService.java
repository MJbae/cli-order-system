package kr.co._29cm.homework.application.interfaces;

import kr.co._29cm.homework.application.GeneralOrderService;
import kr.co._29cm.homework.domain.Order;

import java.util.List;

/**
 * 주문에 대한 비니지스 로직을 처리한다
 * <p>
 * All Known Implementing Classes:
 * @see GeneralOrderService
 * </p>
 */
public interface OrderService<T extends Order, S> {
    /**
     * Order 엔티티에 대한 DTO를 받아서 Order 엔티티를 반환한다.
     * <p>
     * @param orderDtos 주문 정보에 대한 DTO를 요소로 갖는 콜렉션
     * @return Order Order 엔티티를 상속한 객체
     * </p>
     */
    T order(final List<S> orderDtos);
}

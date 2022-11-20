package kr.co._29cm.homework.application.interfaces;

import kr.co._29cm.homework.domain.Order;

import java.util.List;

public interface OrderService<T extends Order, S> {
    T order(final List<S> orderDtos);
}

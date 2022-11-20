package kr.co._29cm.homework.application.interfaces;

import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.OrderItem;

import java.util.List;

public interface OrderItemService<T extends OrderItem, S extends Order> {
    List<T> loadOneBy(S order);

    void save(T orderItem);
}

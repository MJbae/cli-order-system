package kr.co._29cm.homework.cli.interfaces;

import kr.co._29cm.homework.cli.printer.OrderProductPrinter;
import kr.co._29cm.homework.domain.Order;

/**
 * 주문상품에 대한 처리 결과를 출력한다
 * <p>
 * All Known Implementing Classes:
 * @see OrderProductPrinter
 * </p>
 */
public interface OrderItemPrinter<T extends Order> {
    void showBy(T order);
}

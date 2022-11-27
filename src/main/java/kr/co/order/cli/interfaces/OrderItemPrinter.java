package kr.co.order.cli.interfaces;

import kr.co.order.cli.printer.OrderProductPrinter;
import kr.co.order.domain.Order;

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

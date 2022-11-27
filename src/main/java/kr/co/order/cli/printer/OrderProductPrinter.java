package kr.co.order.cli.printer;

import kr.co.order.application.OrderProductService;
import kr.co.order.cli.interfaces.OrderItemPrinter;
import kr.co.order.domain.Order;
import kr.co.order.domain.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderProductPrinter implements OrderItemPrinter<Order> {
    private final OrderProductService orderProductService;

    private final BigDecimal deliveryFee = new BigDecimal(2500);

    private final BigDecimal amountLimit = new BigDecimal(50000);

    @Override
    public void showBy(Order order) {
        List<OrderItem> orderItems = orderProductService.loadOneBy(order);

        System.out.println("- - - - - - - - - - - - - - - - - - - -");

        orderItems.forEach(
                orderItem -> System.out.println(getOrderItemMessage(orderItem))
        );

        System.out.println("- - - - - - - - - - - - -- - - - - - -");

        System.out.println(getOrderPriceMessage(orderItems));

        System.out.println("- - - - - - - - - - - - - - - - - - --");

        System.out.println(getPricePayingMessage(order));
    }

    private String getOrderItemMessage(OrderItem orderItem) {
        return orderItem.getItem().getName() + " - " + orderItem.getCount() + "개";
    }

    private String getOrderPriceMessage(List<OrderItem> orderItems) {
        BigDecimal totalPrice = new BigDecimal(0);
        for (OrderItem orderItem : orderItems) {
            BigDecimal priceSum = BigDecimal.valueOf(orderItem.getItem().getPrice().longValue() * orderItem.getCount());
            totalPrice = totalPrice.add(priceSum);
        }

        if (totalPrice.longValue() < amountLimit.longValue()) {
            return "주문금액: " + totalPrice + "원 \n" +
                    "배송비: " + deliveryFee.longValue();

        }

        return "주문금액: " + totalPrice + "원";
    }

    private String getPricePayingMessage(Order order) {
        return "지불금액: " + order.getPrice() + "원";
    }
}

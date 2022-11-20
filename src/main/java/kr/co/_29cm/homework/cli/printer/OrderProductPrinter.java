package kr.co._29cm.homework.cli.printer;

import kr.co._29cm.homework.application.OrderProductService;
import kr.co._29cm.homework.cli.interfaces.OrderItemPrinter;
import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.OrderItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class OrderProductPrinter implements OrderItemPrinter<Order> {
    private final OrderProductService orderProductService;

    public OrderProductPrinter(OrderProductService orderProductService) {
        this.orderProductService = orderProductService;
    }

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

    private String getOrderItemMessage(OrderItem orderItem){
        return orderItem.getItem().getName() + " - " + orderItem.getCount() + "개";
    }

    private String getOrderPriceMessage(List<OrderItem> orderItems){
        BigDecimal totalPrice = new BigDecimal(0);
        for (OrderItem orderItem : orderItems){
            BigDecimal priceSum = BigDecimal.valueOf(orderItem.getItem().getPrice().longValue() * orderItem.getCount());
            totalPrice = totalPrice.add(priceSum);
        }

        return "주문금액: " + totalPrice + "원";
    }

    private String getPricePayingMessage(Order order){
        return "지불금액: " + order.getPrice() + "원";
    }
}

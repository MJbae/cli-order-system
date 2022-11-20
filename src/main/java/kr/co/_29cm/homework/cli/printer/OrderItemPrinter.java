package kr.co._29cm.homework.cli.printer;

import kr.co._29cm.homework.application.OrderProductService;
import kr.co._29cm.homework.cli.interfaces.Printer;
import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public class OrderItemPrinter implements Printer {
    private final OrderProductService orderProductService;
    private final Order order;

    public OrderItemPrinter(OrderProductService orderProductService, Order order) {
        this.orderProductService = orderProductService;
        this.order = order;
    }

    @Override
    public void show() {
        List<OrderItem> orderItems = orderProductService.loadOneBy(this.order);

        System.out.println("- - - - - - - - - - - - - - - - - - - -");

        orderItems.forEach(
                orderItem -> System.out.println(getOrderItemMessage(orderItem))
        );

        System.out.println("- - - - - - - - - - - - -- - - - - - -");

        System.out.println(getOrderPriceMessage(orderItems));

        System.out.println("- - - - - - - - - - - - - - - - - - --");

        System.out.println(getPricePayingMessage());
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

    private String getPricePayingMessage(){
        return "지불금액: " + this.order.getPrice() + "원";
    }
}

package kr.co._29cm.homework.cli.printer;

import kr.co._29cm.homework.application.OrderItemService;
import kr.co._29cm.homework.cli.interfaces.Printer;
import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public class OrderItemPrinter implements Printer {
    private final OrderItemService orderItemService;
    private final Order order;

    public OrderItemPrinter(OrderItemService orderItemService, Order order) {
        this.orderItemService = orderItemService;
        this.order = order;
    }

    @Override
    public void show() {
        List<OrderItem> orderItems = orderItemService.loadOneBy(this.order);

        System.out.println("- - - - - - - - - - - - - - - - - - - -");

        orderItems.forEach(
                orderItem -> System.out.println(orderItemMessage(orderItem))
        );

        System.out.println("- - - - - - - - - - - - -- - - - - - -");

        System.out.println(orderPriceMessage(orderItems));

        System.out.println("- - - - - - - - - - - - - - - - - - --");

        System.out.println(pricePayingMessage());
    }

    private String orderItemMessage (OrderItem orderItem){
        return orderItem.getItem().getName() + " - " + orderItem.getCount() + "개";
    }

    private String orderPriceMessage (List<OrderItem> orderItems){
        BigDecimal totalPrice = new BigDecimal(0);
        for (OrderItem orderItem : orderItems){
            BigDecimal priceSum = BigDecimal.valueOf(orderItem.getItem().getPrice().longValue() * orderItem.getCount());
            totalPrice = totalPrice.add(priceSum);
        }

        return "주문금액: " + totalPrice + "원";
    }

    private String pricePayingMessage (){
        return "지불금액: " + this.order.getPrice() + "원";
    }
}

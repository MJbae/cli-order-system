package kr.co._29cm.homework.cli;

import kr.co._29cm.homework.OrderItemService;
import kr.co._29cm.homework.cli.interfaces.Printer;
import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
        List<OrderItem> orderItems = orderItemService.loadBy(this.order);

        System.out.println("- - - - - - - - - - - - -");
        for (OrderItem item : orderItems) {
            System.out.println(item.toString());
        }
        System.out.println("- - - - - - - - - - - - -");
        System.out.println("주문금액: " + this.order.getPrice());
        System.out.println("- - - - - - - - - - - - -");
        System.out.println("지불금액: " + this.order.getPrice());
    }
}

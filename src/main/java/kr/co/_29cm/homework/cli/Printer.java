package kr.co._29cm.homework.cli;

import kr.co._29cm.homework.ItemService;
import kr.co._29cm.homework.OrderItemService;
import kr.co._29cm.homework.OrderService;
import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Printer {
    private final ItemService itemService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    public Printer(ItemService itemService, OrderService orderService, OrderItemService orderItemService) {
        this.itemService = itemService;
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }

    public void printGoodByeMessage() {
        System.out.println("종료");
    }

    // TODO: 표준출력 부분을 Wrapper로 감싸서 재사용하는 방향으로 리팩토링 필요
    public void printItemsAll() {
        itemService.loadAll()
                .forEach(item -> System.out.println(item.toString()));
    }

    public void printItemsOrdered(Order order) {
        List<OrderItem> orderItems = orderItemService.loadBy(order);

        System.out.println("- - - - - - - - - - - - -");
        for (OrderItem item : orderItems){
            System.out.println(item.toString());
        }
        System.out.println("- - - - - - - - - - - - -");
        System.out.println("주문금액: " + order.getPrice());
        System.out.println("- - - - - - - - - - - - -");
        System.out.println("지불금액: " + order.getPrice());
    }

    public void printItemOrdered(String item) {
        System.out.println(item + " is ordered ");
    }

    public void printCountOrdered(String count) {
        System.out.println("count of items ordered is " + count);
    }
}

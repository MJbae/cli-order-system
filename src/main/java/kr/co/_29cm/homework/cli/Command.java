package kr.co._29cm.homework.cli;

import kr.co._29cm.homework.application.ItemService;
import kr.co._29cm.homework.application.OrderItemService;
import kr.co._29cm.homework.application.OrderService;
import kr.co._29cm.homework.cli.printer.ByePrinter;
import kr.co._29cm.homework.cli.printer.ItemPrinter;
import kr.co._29cm.homework.cli.printer.OrderItemPrinter;
import kr.co._29cm.homework.cli.prompt.ItemIdPrompt;
import kr.co._29cm.homework.cli.prompt.OrderCountPrompt;
import kr.co._29cm.homework.domain.Order;
import lombok.RequiredArgsConstructor;
import org.jline.utils.InputStreamReader;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.commands.Quit;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@ShellComponent
@RequiredArgsConstructor
public class Command implements Quit.Command {
    private final ItemIdPrompt itemPrompt;
    private final OrderCountPrompt orderCountPrompt;
    private final OrderService orderService;
    private final ItemService itemService;
    private final OrderItemService orderItemService;

    private final List<OrderDto> orderDtos = new ArrayList<>();

    @ShellMethod(key = {"order", "o"}, value = "order")
    public void order() {
        new ItemPrinter(itemService).show();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                itemPrompt.display();
                String itemInput = reader.readLine();

                if (itemInput.equals(" ")) {
                    Order order = orderService.order(orderDtos);
                    new OrderItemPrinter(orderItemService, order).show();
                    orderDtos.clear();
                    break;
                }

                orderCountPrompt.display();
                String countInput = reader.readLine();

                orderDtos.add(new OrderDto(Long.parseLong(itemInput), Integer.parseInt(countInput)));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ShellMethod(key = {"quit", "q"}, value = "quit")
    public void quit() {
        new ByePrinter().show();
        System.exit(0);
    }
}

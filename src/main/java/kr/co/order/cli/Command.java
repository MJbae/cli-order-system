package kr.co.order.cli;

import kr.co.order.application.GeneralOrderService;
import kr.co.order.cli.printer.ByePrinter;
import kr.co.order.cli.printer.ItemPrinter;
import kr.co.order.cli.printer.OrderProductPrinter;
import kr.co.order.cli.prompt.ItemIdPrompt;
import kr.co.order.cli.prompt.OrderCountPrompt;
import kr.co.order.domain.Order;
import kr.co.order.exception.SoldOutException;
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
    private final GeneralOrderService generalOrderService;
    private final ItemIdPrompt itemPrompt;
    private final OrderCountPrompt orderCountPrompt;
    private final ByePrinter byePrinter;
    private final ItemPrinter itemPrinter;
    private final OrderProductPrinter orderProductPrinter;
    private final List<OrderDto> orderDtos = new ArrayList<>();

    @ShellMethod(key = {"order", "o"}, value = "order")
    public void order() {
        itemPrinter.show();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                itemPrompt.display();
                String itemInput = reader.readLine();

                if (itemInput.equals(" ")) {
                    Order order = generalOrderService.order(orderDtos);
                    orderProductPrinter.showBy(order);
                    orderDtos.clear();
                    break;
                }

                orderCountPrompt.display();
                String countInput = reader.readLine();

                orderDtos.add(new OrderDto(Long.parseLong(itemInput), Integer.parseInt(countInput)));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SoldOutException e){
            orderDtos.clear();
            System.out.println(e.getMessage());
        }
    }

    @ShellMethod(key = {"quit", "q"}, value = "quit")
    public void quit() {
        byePrinter.show();
        System.exit(0);
    }
}

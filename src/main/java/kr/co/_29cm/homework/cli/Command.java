package kr.co._29cm.homework.cli;

import kr.co._29cm.homework.application.GeneralOrderService;
import kr.co._29cm.homework.cli.printer.ByePrinter;
import kr.co._29cm.homework.cli.printer.ItemPrinter;
import kr.co._29cm.homework.cli.printer.OrderProductPrinter;
import kr.co._29cm.homework.cli.prompt.ItemIdPrompt;
import kr.co._29cm.homework.cli.prompt.OrderCountPrompt;
import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.exception.SoldOutException;
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

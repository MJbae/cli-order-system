package kr.co._29cm.homework.cli;

import kr.co._29cm.homework.application.ProductService;
import kr.co._29cm.homework.application.OrderProductService;
import kr.co._29cm.homework.application.GeneralOrderService;
import kr.co._29cm.homework.cli.printer.ByePrinter;
import kr.co._29cm.homework.cli.printer.ItemPrinter;
import kr.co._29cm.homework.cli.printer.OrderItemPrinter;
import kr.co._29cm.homework.cli.prompt.ItemIdPrompt;
import kr.co._29cm.homework.cli.prompt.OrderCountPrompt;
import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.exception.SoldOutException;
import lombok.RequiredArgsConstructor;
import org.jline.utils.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final GeneralOrderService generalOrderService;
    private final ProductService productService;
    private final OrderProductService orderProductService;

    private final List<OrderDto> orderDtos = new ArrayList<>();

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ShellMethod(key = {"order", "o"}, value = "order")
    public void order() {
        new ItemPrinter(productService).show();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                itemPrompt.display();
                String itemInput = reader.readLine();

                if (itemInput.equals(" ")) {
                    Order order = generalOrderService.order(orderDtos);
                    new OrderItemPrinter(orderProductService, order).show();
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
        new ByePrinter().show();
        System.exit(0);
    }
}

package kr.co._29cm.homework.cli;

import kr.co._29cm.homework.OrderDto;
import kr.co._29cm.homework.OrderService;
import kr.co._29cm.homework.domain.Order;
import org.jline.utils.InputStreamReader;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.commands.Quit;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@ShellComponent
public class Command implements Quit.Command {
    private final Printer printer;
    private final OrderService service;

    private final List<OrderDto> orders = new ArrayList<>();

    public Command(Printer printer, OrderService service) {
        this.printer = printer;
        this.service = service;
    }

    @ShellMethod(key = {"order", "o"}, value = "order")
    public void order() {
        printer.printItemsAll();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            printer.printItemsAll();

            while (true) {
                printer.itemPrompt();
                String itemInput = reader.readLine();

                if (itemInput.equals(" ")) {
                    Order order = service.order(orders.get(0).getItemId(), orders.get(0).getItemCount());
                    printer.printItemsOrdered(order);
                    break;
                }

                printer.countPrompt();
                String countInput = reader.readLine();

                orders.add(new OrderDto(Long.parseLong(itemInput), Integer.parseInt(countInput)));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ShellMethod(key = {"quit", "q"}, value = "quit")
    public void quit() {
        printer.printGoodByeMessage();
        System.exit(0);
    }
}

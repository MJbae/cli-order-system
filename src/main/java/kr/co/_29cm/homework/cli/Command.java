package kr.co._29cm.homework.cli;

import kr.co._29cm.homework.OrderService;
import kr.co._29cm.homework.domain.Order;
import org.jline.utils.InputStreamReader;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.commands.Quit;

import java.io.BufferedReader;
import java.io.IOException;


@ShellComponent
public class Command implements Quit.Command {
    private final Printer printer;
    private final OrderService service;

    public Command(Printer printer, OrderService service) {
        this.printer = printer;
        this.service = service;
    }

    @ShellMethod(key = {"order", "o"}, value = "order")
    public void order() {
        printer.printItemsAll();

        Long itemId = null;
        Integer itemCount = 0;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            printer.printItemsAll();

            while (true) {
                printer.itemPrompt();
                String itemInput = reader.readLine();
                if (!itemInput.equals(" ")){
                    itemId = Long.getLong(itemInput);
                }
                printer.printItemOrdered(itemInput);

                printer.countPrompt();
                String countInput = reader.readLine();
                if (!countInput.equals(" ")){
                    itemCount = Integer.getInteger(countInput);
                }
                printer.printCountOrdered(countInput);

                if (itemInput.equals(" ") && countInput.equals(" ")) {
                    Order order = service.order(itemId, itemCount);
                    printer.printItemsOrdered(order);
                    break;
                }
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

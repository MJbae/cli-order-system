package kr.co._29cm.homework.cli;

import org.jline.utils.InputStreamReader;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.commands.Quit;

import java.io.BufferedReader;
import java.io.IOException;


@ShellComponent
public class Command implements Quit.Command {
    private final Printer printer;

    public Command(Printer printer) {
        this.printer = printer;
    }

    @ShellMethod(key = {"order", "o"}, value = "order")
    public void order() {
        printer.printItemsAll();

        String itemInput;
        String countInput;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            printer.printItemsAll();

            while (true) {
                printer.itemPrompt();
                itemInput = reader.readLine();
                printer.printItemOrdered(itemInput);

                printer.countPrompt();
                countInput = reader.readLine();
                printer.printCountOrdered(countInput);

                if (itemInput.equals(" ") && countInput.equals(" ")) {
                    printer.printItemsOrdered();
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

package kr.co._29cm.homework.cli;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.commands.Quit;


@ShellComponent
public class Command implements Quit.Command {
    private final Printer printer;

    public Command(Printer printer) {
        this.printer = printer;
    }

    @ShellMethod(key = {"order", "o"}, value = "order")
    public void order() {
        printer.printItemsAll();
    }

    @ShellMethod(key = {"quit", "q"}, value = "quit")
    public void quit() {
        printer.printGoodByeMessage();
        System.exit(0);
    }
}

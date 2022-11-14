package kr.co._29cm.homework.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;


@ShellComponent
public class Command {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @ShellMethod(key = {"order", "o"}, value = "order")
    public void order() {
        log.info("주문 시작");
    }

    @ShellMethod(key = {"quit", "q"}, value = "quit")
    public void quit() {
        log.info("주문 종료");
    }
}

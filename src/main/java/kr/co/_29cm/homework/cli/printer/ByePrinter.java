package kr.co._29cm.homework.cli.printer;

import kr.co._29cm.homework.cli.interfaces.Printer;
import org.springframework.stereotype.Component;

@Component
public class ByePrinter implements Printer {
    public final String goodBye = "주문해주셔서 감사합니다.";

    @Override
    public void show() {
        System.out.println(goodBye);
    }
}

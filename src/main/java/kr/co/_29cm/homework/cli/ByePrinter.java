package kr.co._29cm.homework.cli;

import kr.co._29cm.homework.cli.interfaces.Printer;

public class ByePrinter implements Printer {
    public final String GOOD_BYE = "주문해주셔서 감사합니다.";
    @Override
    public void show() {
        System.out.println(GOOD_BYE);
    }
}

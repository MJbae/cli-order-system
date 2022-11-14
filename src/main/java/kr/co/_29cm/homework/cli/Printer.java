package kr.co._29cm.homework.cli;

import org.springframework.stereotype.Component;

@Component
public class Printer {

    public void printGoodByeMessage() {
        System.out.println("종료");
    }

    public void printItemsAll() {
        System.out.println("Item All");
    }

    public void printItemsOrdered() {
        System.out.println("items ordered ");
    }
}

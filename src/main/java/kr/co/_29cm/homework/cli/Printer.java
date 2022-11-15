package kr.co._29cm.homework.cli;

import kr.co._29cm.homework.ItemService;
import org.springframework.stereotype.Component;

@Component
public class Printer {
    private final ItemService itemService;

    public Printer(ItemService itemService) {
        this.itemService = itemService;
    }

    public void printGoodByeMessage() {
        System.out.println("종료");
    }

    public void printItemsAll() {
        itemService.loadAll().forEach(Object::toString);
    }

    public void printItemsOrdered() {
        System.out.println("items ordered ");
    }

    public void printItemOrdered(String item) {
        System.out.println(item + " is ordered ");
    }

    public void printCountOrdered(String count) {
        System.out.println("count of items ordered is " + count);
    }

    public void itemPrompt() {
        System.out.print("상품번호> ");
    }

    public void countPrompt() {
        System.out.print("상품수량> ");
    }
}

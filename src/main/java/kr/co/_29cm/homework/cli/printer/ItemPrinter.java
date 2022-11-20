package kr.co._29cm.homework.cli.printer;

import kr.co._29cm.homework.application.ItemService;
import kr.co._29cm.homework.cli.interfaces.Printer;
import kr.co._29cm.homework.domain.Item;

public class ItemPrinter implements Printer {

    private final ItemService itemService;

    public ItemPrinter(ItemService itemService){
        this.itemService = itemService;
    }

    private final String LIST_CATEGORY = "상품번호                 상품명               판매가격                재고수";

    @Override
    public void show() {
        System.out.println(LIST_CATEGORY);
        itemService.loadAll()
                .forEach(item -> System.out.println(getItemMessage(item)));
    }

    private String getItemMessage(Item item){
        return item.getId() + "    " + item.getName() + "     " + item.getPrice() + "     " + item.getStockQuantity();
    }
}

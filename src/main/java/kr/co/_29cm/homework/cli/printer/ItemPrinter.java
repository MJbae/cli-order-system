package kr.co._29cm.homework.cli.printer;

import kr.co._29cm.homework.application.ProductService;
import kr.co._29cm.homework.cli.interfaces.Printer;
import kr.co._29cm.homework.domain.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemPrinter implements Printer {

    private final ProductService productService;

    public ItemPrinter(ProductService productService){
        this.productService = productService;
    }

    private final String listCategory = "상품번호                 상품명               판매가격                재고수";

    @Override
    public void show() {
        System.out.println(listCategory);
        productService.loadAll()
                .forEach(item -> System.out.println(getItemMessage(item)));
    }

    private String getItemMessage(Item item){
        return item.getId() + "    " + item.getName() + "     " + item.getPrice() + "     " + item.getStockQuantity();
    }
}

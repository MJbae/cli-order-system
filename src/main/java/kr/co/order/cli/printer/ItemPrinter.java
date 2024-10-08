package kr.co.order.cli.printer;

import kr.co.order.application.ProductService;
import kr.co.order.cli.interfaces.Printer;
import kr.co.order.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemPrinter implements Printer {
    private final ProductService productService;

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

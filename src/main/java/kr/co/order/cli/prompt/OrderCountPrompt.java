package kr.co.order.cli.prompt;

import kr.co.order.cli.interfaces.Prompt;
import org.springframework.stereotype.Component;

@Component
public class OrderCountPrompt implements Prompt {
    public final String orderCountPrompt = "주문수량: ";

    @Override
    public void display() {
        System.out.print(orderCountPrompt);
    }
}

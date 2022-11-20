package kr.co._29cm.homework.cli.prompt;

import kr.co._29cm.homework.cli.interfaces.Prompt;
import org.springframework.stereotype.Component;

@Component
public class OrderCountPrompt implements Prompt {
    public final String orderCountPrompt = "주문수량: ";

    @Override
    public void display() {
        System.out.print(orderCountPrompt);
    }
}

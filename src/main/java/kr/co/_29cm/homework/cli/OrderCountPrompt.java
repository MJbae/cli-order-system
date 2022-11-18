package kr.co._29cm.homework.cli;

import kr.co._29cm.homework.cli.interfaces.Prompt;
import org.springframework.stereotype.Component;

@Component
public class OrderCountPrompt implements Prompt {
    public final String ORDER_COUNT_PROMPT = "주문수량: ";

    @Override
    public void display() {
        System.out.print(ORDER_COUNT_PROMPT);
    }
}

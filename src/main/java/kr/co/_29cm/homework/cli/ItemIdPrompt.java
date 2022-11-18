package kr.co._29cm.homework.cli;

import kr.co._29cm.homework.cli.interfaces.Prompt;
import org.springframework.stereotype.Component;

@Component
public class ItemIdPrompt implements Prompt {
    public final String PRODUCT_PROMPT = "상품번호: ";

    @Override
    public void display() {
        System.out.print(PRODUCT_PROMPT);
    }
}

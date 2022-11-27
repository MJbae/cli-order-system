package kr.co.order.cli.prompt;

import kr.co.order.cli.interfaces.Prompt;
import org.springframework.stereotype.Component;

@Component
public class ItemIdPrompt implements Prompt {
    public final String idPrompt = "상품번호: ";

    @Override
    public void display() {
        System.out.print(idPrompt);
    }
}

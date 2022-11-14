package kr.co._29cm.homework.cli;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class CustomPromptProvider implements PromptProvider {

    @Override
    public AttributedString getPrompt() {
        return new AttributedString("입력[o(주문), q(종료)]: ",
                AttributedStyle.DEFAULT.background(AttributedStyle.BLACK));
    }
}

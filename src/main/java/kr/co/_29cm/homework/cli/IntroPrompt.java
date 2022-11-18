package kr.co._29cm.homework.cli;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class IntroPrompt implements PromptProvider {
    public final String INTRO_MESSAGE = "입력[o(order): 주문, q(quit): 종료]: ";

    @Override
    public AttributedString getPrompt() {
        return new AttributedString(INTRO_MESSAGE,
                AttributedStyle.DEFAULT.background(AttributedStyle.BLACK));
    }
}

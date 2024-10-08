package kr.co.order.cli.prompt;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class IntroPrompt implements PromptProvider {
    public final String introMessage = "입력[o(order): 주문, q(quit): 종료]: ";

    @Override
    public AttributedString getPrompt() {
        return new AttributedString(introMessage,
                AttributedStyle.DEFAULT.background(AttributedStyle.BLACK));
    }
}

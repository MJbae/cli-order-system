package kr.co.order.cli.interfaces;

import kr.co.order.cli.prompt.ItemIdPrompt;
import kr.co.order.cli.prompt.OrderCountPrompt;

/**
 * 사용자 입력에 대한 대기 상태를 표시한다
 * <p>
 * All Known Implementing Classes:
 * @see ItemIdPrompt
 * @see OrderCountPrompt
 * </p>
 */
public interface Prompt {
    void display();
}

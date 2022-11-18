package kr.co._29cm.homework.cli.interfaces;

import kr.co._29cm.homework.cli.prompt.ItemIdPrompt;
import kr.co._29cm.homework.cli.prompt.OrderCountPrompt;

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

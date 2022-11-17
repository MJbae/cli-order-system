package kr.co._29cm.homework.cli;

import org.springframework.stereotype.Component;

/**
 * 명령에 대한 입력 대기 상태를 표시하는 객체
 */
@Component
public class Prompt {
    public void display(String message){
        System.out.print(message);
    }
}

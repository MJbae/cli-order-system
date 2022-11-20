package kr.co._29cm.homework.cli.interfaces;

import kr.co._29cm.homework.cli.printer.ByePrinter;
import kr.co._29cm.homework.cli.printer.ItemPrinter;

/**
 * 사용자 입력에 대한 처리 결과를 출력한다
 * <p>
 * All Known Implementing Classes:
 * @see ByePrinter
 * @see ItemPrinter
 * </p>
 */
public interface Printer {
    void show();
}

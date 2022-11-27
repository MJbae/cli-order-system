package kr.co.order.cli.interfaces;

import kr.co.order.cli.printer.ByePrinter;
import kr.co.order.cli.printer.ItemPrinter;

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

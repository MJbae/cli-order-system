package kr.co._29cm.homework;

import kr.co._29cm.homework.application.OrderService;
import kr.co._29cm.homework.cli.OrderDto;
import kr.co._29cm.homework.exception.SoldOutException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class OrderConcurrencyTest {
    @Autowired
    private OrderService orderService;

    private final List<OrderDto> firstOrderDtos = new ArrayList<>();

    private final Long itemId = 778422L;
    private final int orderItemCount = 1;

    @BeforeEach
    void setUp() {
        firstOrderDtos.add(new OrderDto(itemId, orderItemCount));
    }

    @Test
    void 동시적으로_복수의_주문이_들어오는_상황에서_재고가_부족해지면_재고부족_예외가_발생한다() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(7);
        CountDownLatch countDownLatch = new CountDownLatch(7);

        for (int i = 1; i <= 7; i++) {
            executorService.execute(() -> {
                orderService.order(firstOrderDtos);
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();

        assertThatThrownBy(() -> orderService.order(firstOrderDtos))
                .isInstanceOf(SoldOutException.class);
    }
}

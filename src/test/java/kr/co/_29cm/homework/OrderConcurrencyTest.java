package kr.co._29cm.homework;

import kr.co._29cm.homework.application.ItemService;
import kr.co._29cm.homework.application.OrderService;
import kr.co._29cm.homework.cli.OrderDto;
import kr.co._29cm.homework.domain.Item;
import kr.co._29cm.homework.exception.SoldOutException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OrderConcurrencyTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemService itemService;

    private final List<OrderDto> firstOrderDtos = new ArrayList<>();

    private final Long itemId = 778422L;
    private final int orderItemCount = 1;
    private int stockQuantity;

    @BeforeEach
    void setUp() {
        Item item = itemService.loadOneBy(itemId);
        stockQuantity = item.getStockQuantity();
        firstOrderDtos.add(new OrderDto(itemId, orderItemCount));
    }

    @Test
    @DisplayName("동시적으로 복수의 주문이 들어온다면 재고수만큼만 주문에 성공하고 나머지 주문에서는 재고부족 예외가 발생한다")
    void test_in_concurrency_orders_success_only_under_stock_quantity_else_throw_exception() throws InterruptedException {
        AtomicInteger successCount = new AtomicInteger();
        int numberOfExecution = 100;

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(numberOfExecution);

        for (int i = 1; i <= numberOfExecution; i++) {
            executorService.execute(() -> {
                try{
                    orderService.order(firstOrderDtos);
                    successCount.getAndIncrement();
                    System.out.println("성공");
                } catch (SoldOutException soldOutException) {
                    System.out.println("soldOutException");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();

        assertThat(successCount.get()).isEqualTo(stockQuantity);
    }
}

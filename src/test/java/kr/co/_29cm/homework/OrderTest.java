package kr.co._29cm.homework;

import kr.co._29cm.homework.application.ProductService;
import kr.co._29cm.homework.application.OrderProductService;
import kr.co._29cm.homework.application.GeneralOrderService;
import kr.co._29cm.homework.cli.OrderDto;
import kr.co._29cm.homework.domain.Item;
import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.OrderItem;
import kr.co._29cm.homework.exception.SoldOutException;
import kr.co._29cm.homework.infra.OrderItemRepository;
import kr.co._29cm.homework.infra.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@DisplayName("OrderService")
@Transactional
class OrderTest {
    private GeneralOrderService service;
    private OrderProductService orderProductService;

    private final OrderRepository repository = mock(OrderRepository.class);
    private final OrderItemRepository orderItemRepository = mock(OrderItemRepository.class);
    private final ProductService productService = mock(ProductService.class);
    private final Order orderMocking = mock(Order.class);
    private Item item;
    private OrderItem orderItem;
    private OrderItem secondOrderItem;
    private int orderCount;
    private BigDecimal itemPrice;
    private List<OrderDto> orderDtos;
    private int stockQuantity;
    private final Long itemId = 778422L;
    private final String itemName = "캠핑덕 우드롤테이블";
    private final BigDecimal deliveryFee = BigDecimal.valueOf(2500);

    @BeforeEach
    void setUp() {
        orderDtos = new ArrayList<>();
        orderProductService = new OrderProductService(orderItemRepository);
        service = new GeneralOrderService(repository, orderProductService, productService);
    }

    @Nested
    @DisplayName("order 메소드는")
    class Describe_order {

        @Nested
        @DisplayName("만약 주문금액이 5만원 이상이면")
        class Context_with_order_price_higher_than_or_equal_to_50000 {
            @BeforeEach
            void setUp() {
                orderCount = 2;
                stockQuantity = 3;
                itemPrice = BigDecimal.valueOf(45000);

                item = new Item(itemId, itemName, itemPrice, stockQuantity);
                orderItem = new OrderItem(orderMocking, item, orderCount);

                given(productService.loadOneBy(any())).willReturn(item);
            }

            @Test
            @DisplayName("주문 엔티티의 price에 배송료를 포함하지 않고 반환한다")
            void it_returns_price_not_including_delivery_fee() {
                orderDtos.add(new OrderDto(itemId, orderCount));
                Order order = service.order(orderDtos);

                BigDecimal actualPrice = order.getPrice();
                BigDecimal expectedPrice = itemPrice.multiply(BigDecimal.valueOf(orderCount));

                assertThat(actualPrice).isEqualTo(expectedPrice);
            }
        }

        @Nested
        @DisplayName("만약 주문금액이 5만원 미만이면")
        class Context_with_order_price_lower_than_50000 {
            @BeforeEach
            void setUp() {
                orderCount = 1;
                stockQuantity = 3;
                itemPrice = BigDecimal.valueOf(45000);

                item = new Item(itemId, itemName, itemPrice, stockQuantity);
                orderItem = new OrderItem(orderMocking, item, orderCount);

                given(productService.loadOneBy(any())).willReturn(item);
            }

            @Test
            @DisplayName("주문 엔티티의 price에 배송료를 포함하여 반환한다")
            void it_returns_price_including_delivery_fee() {
                orderDtos.add(new OrderDto(itemId, orderCount));
                Order order = service.order(orderDtos);

                BigDecimal actualPrice = order.getPrice();
                BigDecimal expectedPrice = itemPrice.multiply(BigDecimal.valueOf(orderCount)).add(deliveryFee);

                assertThat(actualPrice).isEqualTo(expectedPrice);
            }
        }

        @Nested
        @DisplayName("만약 두 개 이상의 상품을 주문하면")
        class Context_with_more_than_two_items {
            @BeforeEach
            void setUp() {
                orderCount = 1;
                stockQuantity = 3;
                itemPrice = BigDecimal.valueOf(45000);

                item = new Item(itemId, itemName, itemPrice, stockQuantity);
                orderItem = new OrderItem(orderMocking, item, orderCount);
                secondOrderItem = new OrderItem(orderMocking, item, orderCount);

                given(productService.loadOneBy(any())).willReturn(item);
            }

            @Test
            @DisplayName("모든 상품의 구입액을 주문 엔티티의 price에 반영하여 반환한다")
            void it_returns_price_including_every_items_ordered() {
                orderDtos.add(new OrderDto(itemId, orderCount));
                orderDtos.add(new OrderDto(itemId, orderCount));
                Order order = service.order(orderDtos);

                BigDecimal actualPrice = order.getPrice();
                BigDecimal expectedPrice = itemPrice.multiply(BigDecimal.valueOf(orderCount));
                expectedPrice = expectedPrice.add(itemPrice.multiply(BigDecimal.valueOf(orderCount)));

                assertThat(actualPrice).isEqualTo(expectedPrice);
            }
        }

        @Nested
        @DisplayName("만약 상품의 재고 보다 많은 상품을 주문한다면")
        class Context_with_order_item_more_than_its_stock_quantity {
            @BeforeEach
            void setUp() {
                orderCount = 4;
                stockQuantity = 3;
                itemPrice = BigDecimal.valueOf(45000);

                item = new Item(itemId, itemName, itemPrice, stockQuantity);
                orderItem = new OrderItem(orderMocking, item, orderCount);

                given(productService.loadOneBy(any())).willReturn(item);
            }

            @Test
            @DisplayName("재고부족 예외를 발생시킨다")
            void it_returns_price_including_every_items_ordered() {
                orderDtos.add(new OrderDto(itemId, orderCount));

                assertThatThrownBy(() -> service.order(orderDtos))
                        .isInstanceOf(SoldOutException.class);
            }
        }
    }
}

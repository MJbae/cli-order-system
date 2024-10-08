package kr.co.order.application;

import kr.co.order.application.interfaces.OrderService;
import kr.co.order.cli.OrderDto;
import kr.co.order.exception.SoldOutException;
import kr.co.order.infra.OrderRepository;
import kr.co.order.domain.Item;
import kr.co.order.domain.Order;
import kr.co.order.domain.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GeneralOrderService implements OrderService<Order, OrderDto> {
    private final OrderRepository repository;
    private final OrderProductService orderProductService;
    private final ProductService productService;

    private final BigDecimal deliveryFee = new BigDecimal(2500);
    private final BigDecimal amountLimit = new BigDecimal(50000);

    public Order order(final List<OrderDto> orderDtos) {
        Order order = new Order();
        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalPrice = new BigDecimal(0);

        for (OrderDto orderDto : orderDtos) {
            Item item = productService.loadOneBy(orderDto.getItemId());
            int itemCountOrdering = orderDto.getItemCount();
            int itemStockQuantity = item.getStockQuantity();

            if (itemCountOrdering > itemStockQuantity) {
                throw new SoldOutException();
            }

            item.decreaseStock(itemCountOrdering);

            OrderItem orderItem = new OrderItem(order, item, itemCountOrdering);
            orderItems.add(orderItem);

            BigDecimal priceSum = orderItem.getPriceByMultiplyingCount();
            totalPrice = totalPrice.add(priceSum);

            productService.save(item);
        }

        addDeliveryFeeByTotalPrice(order, totalPrice, amountLimit, deliveryFee);

        this.save(order, orderItems);

        return order;
    }

    private void addDeliveryFeeByTotalPrice(Order order, BigDecimal totalPrice,
                                            BigDecimal amountLimit, BigDecimal deliveryFee) {
        if (totalPrice.longValue() < amountLimit.longValue()) {
            order.markPrice(totalPrice.add(deliveryFee));
            return;
        }

        order.markPrice(totalPrice);
    }

    private void save(Order order, List<OrderItem> orderItems) {
        repository.save(order);

        for (OrderItem orderItem : orderItems) {
            orderProductService.save(orderItem);
        }

    }
}

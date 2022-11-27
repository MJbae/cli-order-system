package kr.co.order.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Table(name = "order_item")
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;
    public OrderItem(Order order, Item item, int count){
        this.order = order;
        this.item = item;
        this.count = count;
    }

    public BigDecimal getPriceByMultiplyingCount(){
        return BigDecimal.valueOf(this.item.getPrice().longValue() * this.count);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                '}';
    }
}

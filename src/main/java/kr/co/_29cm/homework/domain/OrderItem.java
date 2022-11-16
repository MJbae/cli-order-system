package kr.co._29cm.homework.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "order_item")
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_item_id")
    private Item item;

    private int count;
    public OrderItem(Order order, Item item, int count){
        this.order = order;
        this.item = item;
        this.count = count;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "item=" + item.toString() +
                ", count=" + count +
                '}';
    }
}

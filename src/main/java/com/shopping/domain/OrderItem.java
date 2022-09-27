package com.shopping.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order_item_id")
    private Long id;
    private Integer orderPrice;
    private Integer orderCount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public OrderItem() {

    }

    public OrderItem(Integer orderPrice, Integer orderCount, Order order, Item item) {
        this.orderPrice = orderPrice;
        this.orderCount = orderCount;
        this.order = order;
        this.item = item;
    }
}

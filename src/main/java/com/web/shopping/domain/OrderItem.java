package com.web.shopping.domain;

import javax.persistence.*;

@Entity
@Table(name = "tOrderItem")
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order_item_id")
    private Long id;

}

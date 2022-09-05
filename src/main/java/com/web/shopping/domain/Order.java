package com.web.shopping.domain;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
@Table(name = "Orders")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;
}

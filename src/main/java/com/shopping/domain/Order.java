package com.shopping.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Orders")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    public Order() {

    }

    public Order(Member member, Delivery delivery) {
        this.member = member;
        this.delivery = delivery;
    }

}

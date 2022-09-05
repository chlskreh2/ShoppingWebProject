package com.web.shopping.domain;

import javax.persistence.*;

@Entity
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "coupon_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}

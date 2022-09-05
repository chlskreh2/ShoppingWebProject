package com.web.shopping.domain;

import javax.persistence.*;

@Entity
public class CustomerCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "customer_center_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}

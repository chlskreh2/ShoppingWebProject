package com.web.shopping.domain;

import javax.persistence.*;

@Entity
public class Basket {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "basket_id")
    private Long id;
    @OneToOne(mappedBy = "basket")
    private Member member;

}

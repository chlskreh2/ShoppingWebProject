package com.web.shopping.domain;

import javax.persistence.*;

@Entity
public class Delivery {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "delivery_id")
    private Long id;
    @Embedded
    private Address address;
    @Enumerated
    private DeliveryStatus status;
}

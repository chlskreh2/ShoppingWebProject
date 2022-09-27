package com.shopping.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "delivery_id")
    private Long id;
    @Embedded
    private Address address;
    @Enumerated
    private DeliveryStatus status;
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;


}

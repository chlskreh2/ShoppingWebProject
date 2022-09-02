package com.web.shopping.domain;

import javax.persistence.*;

@Entity
@Table(name = "tItem")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "item_id")
    private Long id;
}

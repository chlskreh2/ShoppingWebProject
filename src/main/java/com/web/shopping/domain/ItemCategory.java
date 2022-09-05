package com.web.shopping.domain;

import javax.persistence.*;

@Entity
public class ItemCategory {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "item_category_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne
    @JoinColumn(name = "categroy_id")
    private Category category;

}

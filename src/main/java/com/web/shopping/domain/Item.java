package com.web.shopping.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "item_id")
    private Long id;
    private Integer price;
    private Integer stock;
    @OneToMany(mappedBy = "item")
    private List<ItemComment> itemComments = new ArrayList<>();
}

package com.shopping.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
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

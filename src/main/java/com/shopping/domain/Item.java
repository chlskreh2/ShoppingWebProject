package com.shopping.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "item_id")
    private Long id;
    private String itemName;
    private Integer price;
    private Integer stock;
    private Integer deliveryPrice;
    @Lob
    private String information;

    @OneToMany(mappedBy = "item")
    private List<ItemComment> itemComments = new ArrayList<>();
}

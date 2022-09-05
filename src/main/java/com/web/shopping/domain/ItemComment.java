package com.web.shopping.domain;

import javax.persistence.*;

@Entity
public class ItemComment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "item_comment_id")
    private Long id;
}

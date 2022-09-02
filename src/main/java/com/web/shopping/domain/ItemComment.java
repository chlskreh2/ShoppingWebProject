package com.web.shopping.domain;

import javax.persistence.*;

@Entity
@Table(name = "tItemComment")
public class ItemComment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "item_comment_id")
    private Long id;
}

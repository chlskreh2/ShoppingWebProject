package com.shopping.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
public class Basket {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "basket_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public Basket() {
    }

    public Basket(Member member, Item item) {
        this.member = member;
        this.item = item;
    }
}

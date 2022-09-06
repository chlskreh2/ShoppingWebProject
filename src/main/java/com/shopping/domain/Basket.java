package com.shopping.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Basket {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "basket_id")
    private Long id;
    @OneToOne(mappedBy = "basket", fetch = FetchType.LAZY)
    private Member member;

}

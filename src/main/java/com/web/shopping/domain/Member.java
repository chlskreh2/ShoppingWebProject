package com.web.shopping.domain;

import javax.persistence.*;

@Entity
@Table(name = "tMember")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "member_id")
    private Long id;
}

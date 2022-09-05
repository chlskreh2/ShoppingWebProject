package com.web.shopping.domain;

import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "category_id")
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();
    //연관관계 메소드
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }
}

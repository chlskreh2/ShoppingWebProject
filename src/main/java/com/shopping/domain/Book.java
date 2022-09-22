package com.shopping.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Book extends Item {

    private String writer;
    private String publisher;
    private LocalDate datePublish;

}

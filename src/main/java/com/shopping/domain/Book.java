package com.shopping.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Book extends Item{

    private String writer;
    private String publisher;
    private LocalDate datePublish;

}
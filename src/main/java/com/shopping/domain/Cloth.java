package com.shopping.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Cloth extends Item{

    private String brand;
    private String size;
    private LocalDate datePublish;

}
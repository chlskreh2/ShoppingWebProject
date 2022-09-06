package com.shopping.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
public class Address {

    private String city;
    private String home;

    protected Address() {}

    public Address(String city, String home) {
        this.city = city;
        this.home = home;
    }
}

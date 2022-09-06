package com.shopping.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
public class Address {

    private String city;
    private String street;
    private String home;

    protected Address() {}

    public Address(String city, String street, String home) {
        this.city = city;
        this.street = street;
        this.home = home;
    }
}

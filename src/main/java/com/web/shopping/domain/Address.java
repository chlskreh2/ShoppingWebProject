package com.web.shopping.domain;

import javax.persistence.Embeddable;

@Embeddable
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

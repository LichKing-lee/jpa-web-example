package com.yong.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Address {
    @Column
    private String city;

    @Column
    private String street;

    @Column
    private String zipcode;
}

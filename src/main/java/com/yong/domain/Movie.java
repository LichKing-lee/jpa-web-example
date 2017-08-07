package com.yong.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MOVIE")
public class Movie extends Product {
    @Column
    private String director;

    @Column
    private String actor;
}

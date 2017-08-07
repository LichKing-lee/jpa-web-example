package com.yong.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ALBUM")
public class Album extends Product {
    @Column
    private String artist;

    @Column
    private String etc;
}

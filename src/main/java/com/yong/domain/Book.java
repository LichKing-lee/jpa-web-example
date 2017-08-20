package com.yong.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BOOK")
@Data
public class Book extends Product {
    @Column
    private String author;

    @Column
    private String isbn;
}

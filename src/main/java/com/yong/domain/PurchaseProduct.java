package com.yong.domain;

import javax.persistence.*;

@Entity
@Table(name = "purchase_product")
public class PurchaseProduct {
    @Id
    @Column(name = "purchase_product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @Column(name = "order_price")
    private long price;

    @Column(name = "order_count")
    private int count;
}

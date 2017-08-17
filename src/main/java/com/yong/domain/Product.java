package com.yong.domain;

import com.yong.exception.NotEnoughStockException;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "product_type")
@Getter
public abstract class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column
    private long price;

    @Column(name = "stock_quantity")
    private int stockQuantity;

    @OneToMany
    private List<Category> categories;

    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0){
            throw new NotEnoughStockException("need more stock");
        }

        this.stockQuantity = restStock;
    }
}

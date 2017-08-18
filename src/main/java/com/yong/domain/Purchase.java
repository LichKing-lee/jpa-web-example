package com.yong.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Entity
public class Purchase {
    @Id
    @Column(name = "purchase_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    private List<PurchaseProduct> purchaseProducts;

    @OneToOne(mappedBy = "purchase", cascade = CascadeType.ALL)
    private Delivery delivery;

    @Column(name = "order_date")
    private LocalDateTime orderDatetime;

    @Enumerated(EnumType.STRING)
    @Column(name = "purchase_status")
    private PurchaseStatus status;

    public static Purchase createPurchase(Member member, Delivery delivery, PurchaseProduct... products){
        Purchase purchase = new Purchase();
        purchase.member = member;
        purchase.delivery = delivery;
        purchase.purchaseProducts = Arrays.asList(products);
        purchase.status = PurchaseStatus.ORDER;
        purchase.orderDatetime = LocalDateTime.now();

        return purchase;
    }

    public void cancel(){
        if(this.delivery.isComplete()){
            throw new RuntimeException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }

        this.status = PurchaseStatus.CANCEL;
        this.purchaseProducts.forEach(PurchaseProduct::cancel);
    }

    public long getTotalPrice(){
        return this.purchaseProducts.stream()
                .mapToLong(PurchaseProduct::getTotalPrice)
                .sum();
    }
}

package com.yong.domain;

import com.yong.member.Member;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Purchase {
    @Id
    @Column(name = "purchase_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    private List<PurchaseProduct> purchaseProducts;

    @OneToOne(mappedBy = "purchase", cascade = CascadeType.ALL)
    private Delivery delivery;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "purchase_status")
    private PurchaseStatus status;
}

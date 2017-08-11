package com.yong.member;

import com.yong.domain.Address;
import com.yong.domain.Purchase;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_name")
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Purchase> purchases;
}

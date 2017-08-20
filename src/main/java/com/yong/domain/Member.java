package com.yong.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_name")
    @NonNull
    private String name;

    @Embedded
    @Setter
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Purchase> purchases;
}

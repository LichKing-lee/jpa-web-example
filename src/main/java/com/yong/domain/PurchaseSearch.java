package com.yong.domain;

import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class PurchaseSearch {
    private String memberName;
    private PurchaseStatus purchaseStatus;

    public Optional<String> getMemberNameAsOptional(){
        return Optional.ofNullable(this.memberName);
    }

    public Optional<PurchaseStatus> getPurchaseStatusAsOptional(){
        return Optional.ofNullable(this.purchaseStatus);
    }
}

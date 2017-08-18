package com.yong.service;

import com.yong.domain.*;
import com.yong.repository.MemberRepository;
import com.yong.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private ProductService productService;

    public Long order(Long memberId, Long itemId, int count){
        Member member = memberRepository.findOne(memberId);
        Product product =  productService.findOne(itemId);

        Delivery delivery = new Delivery(member.getAddress());
        PurchaseProduct purchaseProduct = PurchaseProduct.createPurchaseProduct(product, count);

        Purchase purchase = Purchase.createPurchase(member, delivery, purchaseProduct);

        purchaseRepository.save(purchase);
        return purchase.getId();
    }

    public void cancelOrder(Long purchaseId){
        Purchase purchase = purchaseRepository.findOne(purchaseId);

        purchase.cancel();
    }

    public List<Purchase> findPurchases(PurchaseSearch search){
        return purchaseRepository.findAll(search);
    }
}

package com.yong.service;

import com.yong.config.spring.AppConfig;
import com.yong.domain.*;
import com.yong.exception.NotEnoughStockException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
public class PurchaseServiceTest {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private PurchaseService purchaseService;

    private Member member;
    private Product product;

    @Before
    public void setUp(){
        this.member = createMember();
        this.product = createBook("JPA", 10000, 10);
    }

    @Test
    public void 상품주문(){
        // given
        int orderCount = 2;

        // when
        Long id = purchaseService.order(member.getId(), product.getId(), orderCount);
        Purchase purchase = purchaseService.findPurchase(id);

        // then
        assertThat(product.getStockQuantity(), is(8));
        assertThat(purchase, is(notNullValue()));
        assertThat(purchase.purchaseProductCount(), is(1));
        assertThat(purchase.getTotalPrice(), is(20000L));
        assertThat(purchase.getStatus(), is(PurchaseStatus.ORDER));
    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과(){
        // given
        int orderCount = 15;

        // when
        Long id = purchaseService.order(member.getId(), product.getId(), orderCount);
        Purchase purchase = purchaseService.findPurchase(id);

        // then
        fail();
    }

    @Test
    public void 주문취소(){
        // given
        int orderCount = 2;
        Long id = purchaseService.order(this.member.getId(), this.product.getId(), orderCount);

        // when
        purchaseService.cancelOrder(id);
        Purchase purchase = purchaseService.findPurchase(id);

        // then
        assertThat(purchase.getStatus(), is(PurchaseStatus.CANCEL));
    }

    private Product createBook(String name, long price, int count) {
        Product book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(count);
        em.persist(book);

        return book;
    }

    private Member createMember() {
        Member member = new Member("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);

        return member;
    }
}
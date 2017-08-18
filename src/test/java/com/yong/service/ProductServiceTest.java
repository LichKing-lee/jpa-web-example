package com.yong.service;

import com.yong.config.spring.AppConfig;
import com.yong.domain.Album;
import com.yong.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    public void 상품등록(){
        Product product = new Album();

        productService.saveProduct(product);

        Product find = productService.findOne(product.getId());

        assertThat(find, is(product));
    }
}
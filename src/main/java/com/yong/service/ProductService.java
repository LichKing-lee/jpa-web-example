package com.yong.service;

import com.yong.domain.Product;
import com.yong.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(Product product){
        productRepository.save(product);
    }

    public List<Product> findProducts(){
        return productRepository.findAll();
    }

    public Product findOne(Long id){
        return productRepository.findOne(id);
    }
}

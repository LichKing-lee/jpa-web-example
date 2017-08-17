package com.yong.repository;

import com.yong.domain.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Repository
public class ProductRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Product product){
        if(Objects.isNull(product.getId())){
            em.persist(product);
        }else{
            em.merge(product);
        }
    }

    public Product findOne(Long id){
        return em.find(Product.class, id);
    }

    public List<Product> findAll(){
        return em.createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
    }
}

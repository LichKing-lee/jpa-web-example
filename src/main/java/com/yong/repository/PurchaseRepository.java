package com.yong.repository;

import com.yong.domain.Member;
import com.yong.domain.Purchase;
import com.yong.domain.PurchaseSearch;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PurchaseRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Purchase purchase){
        em.persist(purchase);
    }

    public Purchase findOne(Long id){
        return em.find(Purchase.class, id);
    }

    public List<Purchase> findAll(PurchaseSearch search){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Purchase> query = builder.createQuery(Purchase.class);
        Root<Purchase> root = query.from(Purchase.class);

        List<Predicate> criteria = new ArrayList<>();

        search.getPurchaseStatusAsOptional()
                .ifPresent(status ->
                    criteria.add(builder.equal(root.get("status"), status))
                );

        search.getMemberNameAsOptional()
                .filter(name -> !name.isEmpty())
                .ifPresent(name -> {
                    Join<Purchase, Member> join = root.join("member", JoinType.INNER);
                    criteria.add(builder.like(join.get("name"), "%" + name + "%"));
                });

        query.where(builder.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Purchase> typedQuery = em.createQuery(query).setMaxResults(1000);

        return typedQuery.getResultList();
    }
}

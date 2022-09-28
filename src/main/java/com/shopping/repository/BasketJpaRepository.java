package com.shopping.repository;

import com.shopping.domain.Basket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class BasketJpaRepository implements BasketRepository{

    private final EntityManager em;

    @Override
    public Long save(Basket basket) {
        em.persist(basket);
        return basket.getId();
    }

    @Override
    public List<Basket> findByMemberId(Long memberId) {
        return em.createQuery("select b from Basket b join fetch b.item where b.member.id = :memberId", Basket.class)
                    .setParameter("memberId", memberId)
                    .getResultList();
    }
}

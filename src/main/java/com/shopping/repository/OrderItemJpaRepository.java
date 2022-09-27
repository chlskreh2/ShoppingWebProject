package com.shopping.repository;

import com.shopping.domain.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class OrderItemJpaRepository implements OrderItemRepository{

    private final EntityManager em;

    @Override
    public Long save(OrderItem orderItem) {
        em.persist(orderItem);
        return orderItem.getId();
    }

}

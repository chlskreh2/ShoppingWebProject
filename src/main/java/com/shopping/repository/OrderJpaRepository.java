package com.shopping.repository;

import com.shopping.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class OrderJpaRepository implements OrderRepository{

    private final EntityManager em;

    @Override
    public Long save(Order order) {
        em.persist(order);
        return order.getId();
    }

    @Override
    public Order findById(Long orderId) {
        return em.find(Order.class, orderId);
    }

    @Override
    public void delete(Long orderId) {
        Order order = findById(orderId);
        em.remove(order);
    }

}

package com.shopping.repository;

import com.shopping.domain.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderItemJpaRepository implements OrderItemRepository{

    private final EntityManager em;

    @Override
    public Long save(OrderItem orderItem) {
        em.persist(orderItem);
        return orderItem.getId();
    }

    @Override
    public OrderItem findById(Long id) {
        return em.find(OrderItem.class, id);
    }

    @Override
    public OrderItem findByOrderId(Long id) {
        List<OrderItem> orderItem = em.createQuery("select oi from OrderItem as oi where oi.order.id = :orderId", OrderItem.class)
                .setParameter("orderId", id)
                .getResultList();
        return orderItem.get(0);
    }

    @Override
    public void delete(Long id) {
        OrderItem orderItem = findByOrderId(id);
        em.remove(orderItem);
    }

}

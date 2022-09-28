package com.shopping.repository;

import com.shopping.domain.OrderItem;

public interface OrderItemRepository {

    Long save(OrderItem orderItem);

    OrderItem findById(Long id);

    void delete(Long id);

    OrderItem findByOrderId(Long id);
}

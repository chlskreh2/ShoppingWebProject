package com.shopping.repository;

import com.shopping.domain.OrderItem;

public interface OrderItemRepository {

    Long save(OrderItem orderItem);

}

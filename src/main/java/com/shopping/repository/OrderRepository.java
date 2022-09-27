package com.shopping.repository;

import com.shopping.domain.Order;

public interface OrderRepository {

    Long save(Order order);

}

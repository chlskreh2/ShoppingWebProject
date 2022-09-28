package com.shopping.repository;

import com.shopping.domain.Basket;

import java.util.List;

public interface BasketRepository {

    Long save(Basket basket);

    List<Basket> findByMemberId(Long memberId);

}

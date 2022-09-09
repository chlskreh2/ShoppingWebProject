package com.shopping.repository;

import com.shopping.domain.Item;

import java.util.List;

public interface ItemRepository {

    public Long save(Item item);
    public Item findById(Long id);
    public List<Item> findAll();

}

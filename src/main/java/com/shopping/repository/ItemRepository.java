package com.shopping.repository;

import com.shopping.domain.Item;
import com.shopping.dto.item.SortViewBookDto;

import java.util.List;

public interface ItemRepository {

    public Long save(Item item);
    public Item findById(Long id);
    public List<Item> findAll();
    public List<SortViewBookDto> findBookSortView();

}

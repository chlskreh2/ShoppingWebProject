package com.shopping.repository;

import com.shopping.domain.Item;
import com.shopping.dto.item.SortViewBookDto;

import java.util.List;

public interface ItemRepository {

     Long save(Item item);
     Item findById(Long id);
//     List<Item> findAll();
     List<SortViewBookDto> findBookSortView();

}

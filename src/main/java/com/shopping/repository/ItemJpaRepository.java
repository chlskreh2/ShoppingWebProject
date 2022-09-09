package com.shopping.repository;

import com.shopping.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemJpaRepository implements ItemRepository{

    private final EntityManager em;

    @Override
    public Long save(Item item) {
        em.persist(item);
        return item.getId();
    }

    @Override
    public Item findById(Long id) {
        return em.find(Item.class, id);
    }

    @Override
    public List<Item> findAll() {
        return em.createQuery("select i from Item as i", Item.class)
                .getResultList();
    }

}

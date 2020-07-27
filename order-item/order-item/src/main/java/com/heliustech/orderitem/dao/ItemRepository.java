package com.heliustech.orderitem.dao;

import com.heliustech.orderitem.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Integer> {
    public Item findByProductCode(String productCode);
}

package com.heliustech.orderitem.dao;

import com.heliustech.orderitem.entity.OrderItemInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderInfoRepository extends JpaRepository<OrderItemInfo,Integer> {

    public List<OrderItemInfo> findOrderByOrderId(String orderId);
}

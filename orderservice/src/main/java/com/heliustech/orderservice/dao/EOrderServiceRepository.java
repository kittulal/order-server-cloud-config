package com.heliustech.orderservice.dao;

import com.heliustech.orderservice.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EOrderServiceRepository extends JpaRepository<CustomerOrder,Integer> {
    public List<CustomerOrder> findOrderServiceByOrderId(String orderId);
    public List<CustomerOrder> findOrderServiceByCustomerId(String customerId);
}

package com.heliustech.orderservice.dao;

import com.heliustech.orderservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ECustomerRepository extends JpaRepository<Customer,Integer> {

    public long countByEmail(String email);

}

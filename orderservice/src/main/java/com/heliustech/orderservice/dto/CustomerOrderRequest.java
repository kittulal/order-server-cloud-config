package com.heliustech.orderservice.dto;

import com.heliustech.orderservice.entity.Customer;

import java.io.Serializable;
import java.util.List;

public class CustomerOrderRequest implements Serializable {
    private Customer customer;
    private List<ItemRequest> itmes;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ItemRequest> getItmes() {
        return itmes;
    }

    public void setItmes(List<ItemRequest> itmes) {
        this.itmes = itmes;
    }
}

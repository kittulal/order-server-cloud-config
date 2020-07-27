package com.heliustech.orderservice.dto;

import com.heliustech.orderservice.entity.Customer;

import java.io.Serializable;
import java.util.Map;

public class CustomerOrderResponse implements Serializable {
    private String orderId;
    private String orderStatus;
    private String message;
    private Double amount;
    private String orderDate;
    private Map<String,Order> orderDetails;
    private Customer customer;


    public CustomerOrderResponse() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Map<String, Order> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Map<String, Order> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

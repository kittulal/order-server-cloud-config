package com.heliustech.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.heliustech.orderservice.entity.Customer;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderInfo implements Serializable {

    private String Id;
    private String orderId;
    private int quantity;
    private double total;
    private String date;
    private List<Item> items;
    private Customer customer;

    public OrderInfo() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public OrderInfo(String id, String orderId, int quantity, double total, String date, List<Item> items, Customer customer) {
        Id = id;
        this.orderId = orderId;
        this.quantity = quantity;
        this.total = total;
        this.date = date;
        this.items = items;
        this.customer = customer;
    }
}

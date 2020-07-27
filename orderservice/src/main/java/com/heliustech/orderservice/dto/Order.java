package com.heliustech.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order  implements Serializable {
    @ApiModelProperty(hidden = true)
    private String id;
    private String orderId;
    private int quantity;
    private double total;
    private Item item;

    public Order() {
    }

    public Order(String id, String orderId, int quantity, double total, Item item) {
        id = id;
        this.orderId = orderId;
        this.quantity = quantity;
        this.total = total;
        this.item = item;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}

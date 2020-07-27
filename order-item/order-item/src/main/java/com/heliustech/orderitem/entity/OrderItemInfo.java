package com.heliustech.orderitem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.heliustech.orderitem.entity.Item;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class OrderItemInfo implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String Id;
    private String orderId;
    private int quantity;
    private double total;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Item.class)
    @JoinColumn(name="product_id", referencedColumnName = "id")
    private Item item;

    public OrderItemInfo() {
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

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}

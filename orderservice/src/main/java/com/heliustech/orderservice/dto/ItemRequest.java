package com.heliustech.orderservice.dto;

import javax.validation.constraints.NotEmpty;

public class ItemRequest {
    @NotEmpty
    private String itemCode;
    @NotEmpty
    private int quantity;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

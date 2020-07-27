package com.heliustech.orderservice.dto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

public class OrderRequest implements Serializable {
    @NotEmpty
    private List<ItemRequest> orderRequestList;

    public OrderRequest() {
    }

    public List<ItemRequest> getOrderRequestList() {
        return orderRequestList;
    }

    public void setOrderRequestList(List<ItemRequest> orderRequestList) {
        this.orderRequestList = orderRequestList;
    }
}

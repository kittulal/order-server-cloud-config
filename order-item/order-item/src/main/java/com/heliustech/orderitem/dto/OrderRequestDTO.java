package com.heliustech.orderitem.dto;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class OrderRequestDTO {
    @NotEmpty
    private List<ItemRequestDTO> orderRequestList;

    public OrderRequestDTO() {
    }

    public List<ItemRequestDTO> getOrderRequestList() {
        return orderRequestList;
    }

    public void setOrderRequestList(List<ItemRequestDTO> orderRequestList) {
        this.orderRequestList = orderRequestList;
    }
}

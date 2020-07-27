package com.heliustech.orderitem.controller;

import com.heliustech.orderitem.entity.OrderItemInfo;
import com.heliustech.orderitem.dto.OrderRequestDTO;
import com.heliustech.orderitem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class OrderItemServiceController {
    @Autowired
    OrderService orderService;

    @GetMapping("/order-items")
    private List<OrderItemInfo> getAllOrderedItems(){
      return orderService.getAllOrdersInfo();
    }

    @GetMapping("/order-item/{order_id}")
    public List<OrderItemInfo> getOrder(@PathVariable("order_id") String orderId){
        return orderService.getOrderInfoByOrderId(orderId);
    }


    @PostMapping("/save-order")
    public ResponseEntity<Map<String,OrderItemInfo>> saveOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        return orderService.saveOrder(orderRequestDTO);
    }
}

package com.heliustech.orderservice.controller;

import com.heliustech.orderservice.config.OrderServiceConfiguration;
import com.heliustech.orderservice.dto.CustomerOrderRequest;
import com.heliustech.orderservice.dto.CustomerOrderResponse;
import com.heliustech.orderservice.entity.CustomerOrder;
import com.heliustech.orderservice.dto.OrderInfo;
import com.heliustech.orderservice.service.EOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class EOrderServiceController {

    @Autowired
    private OrderServiceConfiguration config;

    @Autowired
    private EOrderService eOrderService;

//    @GetMapping("/orders")
    public List<CustomerOrder> getOrders(){
        return eOrderService.getAllOrders();
    }

    @GetMapping("/order-detailed-info/{order_id}")
    public List<OrderInfo> getOrdersByOrderId(@PathVariable(name="order_id")  String orderId){
        return eOrderService.getOrdersByOrderId(orderId);
    }
    @GetMapping("/orders/cutomer/{customer_id}")
    public List<CustomerOrder> getOrdersByCustomerId(@PathVariable(name="customer_id")  String custId){
        return eOrderService.getOrdersByCustomerId(custId);
    }

    @GetMapping("/order-detailed-info")
    public List<OrderInfo> getAllOrdersDetailedInfo(){
        return eOrderService.getAllOrdersDetailedInfo();
    }

    @PostMapping("/post-order")
    public ResponseEntity<CustomerOrderResponse> saveCustomerOrder(@RequestBody CustomerOrderRequest customerOrderRequest) {
        return eOrderService.saveCustomerOrder(customerOrderRequest);
    }
}
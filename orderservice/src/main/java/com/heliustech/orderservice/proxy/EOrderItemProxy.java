package com.heliustech.orderservice.proxy;

import com.heliustech.orderservice.dto.Item;
import com.heliustech.orderservice.dto.Order;
import com.heliustech.orderservice.dto.OrderRequest;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(name="order-item", url="localhost:8090")
public interface EOrderItemProxy {
    @GetMapping("/order-items")
    public List<Order> getAllOrderedItems();

    @GetMapping("/order-item/{order_id}")
    public List<Order> getOrder(@PathVariable("order_id") String orderId);

    @PostMapping("/save-order")
    @Headers("Content-Type: application/json")
    public ResponseEntity<Map<String,Order>> saveOrder(@RequestBody OrderRequest orderRequest);

    @GetMapping("/all-items")
    public List<Item> getAllItems();
}

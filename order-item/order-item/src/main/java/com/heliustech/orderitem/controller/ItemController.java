package com.heliustech.orderitem.controller;

import com.heliustech.orderitem.service.OrderService;
import com.heliustech.orderitem.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {
    @Autowired
    OrderService orderService;

    @GetMapping("/all-items")
    public List<Item> getItems(){
        return orderService.getItems();
    }
}

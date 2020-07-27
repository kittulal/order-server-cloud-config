package com.heliustech.orderservice.controller;

import com.heliustech.orderservice.dto.Item;
import com.heliustech.orderservice.service.EItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EItemController {
    @Autowired
    private EItemService eItemService;

    @GetMapping("view-items")
    public List<Item> getAllItems(){
        return eItemService.getAllItem();
    }
}

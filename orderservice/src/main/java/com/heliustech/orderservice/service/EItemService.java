package com.heliustech.orderservice.service;

import com.heliustech.orderservice.dto.Item;
import com.heliustech.orderservice.proxy.EItemProxy;
import com.heliustech.orderservice.proxy.EOrderItemProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EItemService {
    @Autowired
    private EOrderItemProxy eOrderItemProxy;

    public List<Item> getAllItem(){
        return eOrderItemProxy.getAllItems();
    }
}

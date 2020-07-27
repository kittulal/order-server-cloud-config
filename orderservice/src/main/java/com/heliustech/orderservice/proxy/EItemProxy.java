package com.heliustech.orderservice.proxy;

import com.heliustech.orderservice.dto.Item;
import com.heliustech.orderservice.dto.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//@FeignClient(name="order-item", url="localhost:8090")
public interface EItemProxy {

}

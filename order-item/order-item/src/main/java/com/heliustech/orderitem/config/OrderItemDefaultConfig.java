package com.heliustech.orderitem.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("order-item")
public class OrderItemDefaultConfig {
    private int defaultSequence;
}

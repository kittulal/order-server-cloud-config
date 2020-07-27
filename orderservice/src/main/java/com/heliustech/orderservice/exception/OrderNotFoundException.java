package com.heliustech.orderservice.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(){
        super("OrderNotFoundException");
    }
}

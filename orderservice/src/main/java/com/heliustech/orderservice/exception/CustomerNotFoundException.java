package com.heliustech.orderservice.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(){
        super("CustomerNotFoundException");
    }
}

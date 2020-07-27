package com.heliustech.orderservice.exception;

public class InvalidOrderException extends RuntimeException{
    public InvalidOrderException(){
        super("InvalidOrderException");
    }
}

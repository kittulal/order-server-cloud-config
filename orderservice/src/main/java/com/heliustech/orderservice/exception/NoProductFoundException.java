package com.heliustech.orderservice.exception;

public class NoProductFoundException extends RuntimeException  {
    public NoProductFoundException(){
        super("NoProductFoundException");
    }
}

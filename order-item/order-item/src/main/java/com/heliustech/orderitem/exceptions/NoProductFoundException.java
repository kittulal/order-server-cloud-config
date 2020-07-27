package com.heliustech.orderitem.exceptions;

public class NoProductFoundException extends RuntimeException{
    public NoProductFoundException() {
        super("No PRODUCT data found");
    }
}


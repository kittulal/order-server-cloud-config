package com.heliustech.orderitem.exceptions;

public class InvalidOrderException extends RuntimeException {
    public InvalidOrderException() {
        super("Invalid Order posted");
    }
}

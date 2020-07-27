package com.heliustech.orderitem.exceptions;

public class OrderFetchException extends RuntimeException{
    public OrderFetchException(){
        super("Unable to fetch order details-- exception ocurred");
    }
}

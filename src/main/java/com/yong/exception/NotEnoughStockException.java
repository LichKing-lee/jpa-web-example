package com.yong.exception;

public class NotEnoughStockException extends RuntimeException {
    public NotEnoughStockException(String message){
        super(message);
    }
}

package com.upb.agripos;

public class InsufficientStockException extends Exception {
    public InsufficientStockException(String msg) {
        super(msg);
    }
}

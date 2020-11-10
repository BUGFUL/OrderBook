package com.algoorders.orderbook.controller;

public class NoOrderException extends RuntimeException {

    public NoOrderException(String message) {
        super(message);
    }
}

package com.algoorders.orderbook.service;

import com.algoorders.orderbook.model.Order;
import com.algoorders.orderbook.model.Orders;

import java.util.Optional;

public interface IOrderBookService {

    Orders findAll();
    Optional<Order> findById(Long id);
    Order save(Order order);
    void deleteById(Long id);
    boolean exists(Order order);
}

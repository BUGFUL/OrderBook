package com.orderbook.service;

import com.orderbook.model.Order;
import com.orderbook.model.Orders;

import java.util.Optional;

public interface IOrderBookService {

    Orders findAll();
    Optional<Order> findById(Long id);
    Order save(Order order);
    void deleteById(Long id);
    boolean exists(Order order);
}

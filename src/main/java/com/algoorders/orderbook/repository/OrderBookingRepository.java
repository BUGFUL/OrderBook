package com.algoorders.orderbook.repository;

import com.algoorders.orderbook.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBookingRepository extends CrudRepository<Order, Long> {
}
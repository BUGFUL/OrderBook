package com.orderbook.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.orderbook.amqp.OrderPublisher;
import com.orderbook.model.Order;
import com.orderbook.model.Orders;
import com.orderbook.service.OrderBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    OrderBookService bookService;

    @Autowired
    OrderPublisher orderPublisher;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public Orders findOrders() {
        return bookService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Order> findOrder(@PathVariable Long id) {
        Optional<Order> order = bookService.findById(id);
        if (order.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No order found with id (%s)", id));
        }
        return new ResponseEntity<>(order.get(), HttpStatus.OK);
    }

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Order> createUser(@RequestBody Order order) throws JsonProcessingException {
        orderPublisher.publishOrder(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    //TODO - Implement update Order (with update its version: version.next())

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id) {
        Optional<Order> existed = bookService.findById(id);
        if (existed.isPresent()) {
            bookService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No order exist with id (%s)", id));
        }
    }
}



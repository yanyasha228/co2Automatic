package com.example.co2Automatic.services;

import com.example.co2Automatic.models.Order;

import java.util.List;

public interface OrderService {
    void save(Order order);

    List<Order> getAllOrders();
}

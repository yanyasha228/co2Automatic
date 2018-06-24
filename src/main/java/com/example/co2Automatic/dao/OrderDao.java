package com.example.co2Automatic.dao;

import com.example.co2Automatic.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDao extends JpaRepository<Order, Long> {
    List<Order> findAll();
}

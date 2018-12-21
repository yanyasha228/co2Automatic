package com.example.co2Automatic.Dao;

import com.example.co2Automatic.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderDao extends JpaRepository<Order, Long> {
    List<Order> findAll();
    Optional<Order> findById(Long id);
}

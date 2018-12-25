package com.example.co2Automatic.services;

import com.example.co2Automatic.models.OrderLine;

import java.util.Optional;

public interface OrderLineService {

    Optional<OrderLine> findById(Long id);

    OrderLine saveAndReturnEntity(OrderLine orderLine);

    void deleteById(Long id);

}

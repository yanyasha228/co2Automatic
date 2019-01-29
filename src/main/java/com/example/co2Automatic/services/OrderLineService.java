package com.example.co2Automatic.services;

import com.example.co2Automatic.models.OrderLine;

import java.util.List;
import java.util.Optional;

public interface OrderLineService {

    Optional<OrderLine> findById(Long id);

    OrderLine saveAndReturnEntity(OrderLine orderLine);

    List<OrderLine> findAll();

    void deleteById(Long id);

    void delete(OrderLine orderLine);

    void deleteAll(Iterable<OrderLine> orderLines);

}

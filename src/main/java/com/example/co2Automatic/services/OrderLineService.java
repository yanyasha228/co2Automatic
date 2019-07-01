package com.example.co2Automatic.services;

import com.example.co2Automatic.HelpUtils.CustomExceptions.ImpossibleEntityUpdatingException;
import com.example.co2Automatic.models.OrderLine;

import java.util.List;
import java.util.Optional;

public interface OrderLineService {

    Optional<OrderLine> findById(Long id);

    OrderLine save(OrderLine orderLine);

    List<OrderLine> save(List<OrderLine> orderLines);

    OrderLine update(OrderLine orderLine) throws ImpossibleEntityUpdatingException;

    List<OrderLine> update(List<OrderLine> orderLines) throws ImpossibleEntityUpdatingException;


    List<OrderLine> findAll();

    void delete(Long id);

    void delete(OrderLine orderLine);

    void delete(Iterable<OrderLine> orderLines);

}

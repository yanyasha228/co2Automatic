package com.example.co2Automatic.services;

import com.example.co2Automatic.Dao.OrderLineDao;
import com.example.co2Automatic.models.OrderLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderLineServiceImpl implements OrderLineService {

    @Autowired
    OrderLineDao orderLineDao;

    @Override
    public Optional<OrderLine> findById(Long id) {
        return orderLineDao.findById(id);
    }

    @Override
    public OrderLine saveAndReturnEntity(OrderLine orderLine) {
        return orderLineDao.save(orderLine);
    }

    @Override
    public void deleteById(Long id) {
        orderLineDao.deleteById(id);
    }
}

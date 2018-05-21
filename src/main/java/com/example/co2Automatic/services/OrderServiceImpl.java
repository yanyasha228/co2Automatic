package com.example.co2Automatic.services;

import com.example.co2Automatic.dao.OrderDao;
import com.example.co2Automatic.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Override
    public void save(Order order) {
        orderDao.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return StreamSupport
                .stream(
                        Spliterators.spliteratorUnknownSize(orderDao.findAll().iterator(), Spliterator.NONNULL),
                        false)
                .collect(Collectors.toList());
    }
}

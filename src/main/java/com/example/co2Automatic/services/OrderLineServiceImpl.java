package com.example.co2Automatic.services;

import com.example.co2Automatic.Dao.OrderLineDao;
import com.example.co2Automatic.HelpUtils.CustomExceptions.ImpossibleEntityUpdatingException;
import com.example.co2Automatic.models.OrderLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderLineServiceImpl implements OrderLineService {

    @Autowired
     private OrderLineDao orderLineDao;

    @Override
    public Optional<OrderLine> findById(Long id) {
        return orderLineDao.findById(id);
    }

    @Override
    public OrderLine save(OrderLine orderLine) {
        return orderLineDao.save(orderLine);
    }

    @Override
    public List<OrderLine> save(List<OrderLine> orderLines) {
        return orderLineDao.saveAll(orderLines);
    }

    @Override
    public OrderLine update(OrderLine orderLine) throws ImpossibleEntityUpdatingException {

        if (orderLine.getId() <= 0)
            throw new ImpossibleEntityUpdatingException("Attempt to update entity without ID!!!");

            return orderLineDao.save(orderLine);

    }
///////////////////////
    @Override
    public List<OrderLine> update(List<OrderLine> orderLines) throws ImpossibleEntityUpdatingException {
        for (OrderLine oneOf : orderLines) {
            if (oneOf.getId() <= 0)
                throw new ImpossibleEntityUpdatingException("Attempt to update entity without ID!!!");
        }
        return orderLineDao.saveAll(orderLines);
    }

    @Override
    public List<OrderLine> findAll() {
        return orderLineDao.findAll();
    }

    @Override
    public void delete(Long id) {
        orderLineDao.deleteById(id);
    }

    @Override
    public void delete(OrderLine orderLine) {
        orderLineDao.delete(orderLine);
    }

    @Override
    public void delete(Iterable<OrderLine> orderLines) {
        orderLineDao.deleteAll(orderLines);
    }
}

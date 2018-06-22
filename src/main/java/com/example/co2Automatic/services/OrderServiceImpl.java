package com.example.co2Automatic.services;

import com.example.co2Automatic.dao.OrderDao;
import com.example.co2Automatic.dao.PhoneNumberDao;
import com.example.co2Automatic.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private PhoneNumberService phoneNumberService;

    @Autowired
    ClientService clientService;

    @Override
    public void save(Order order) {
        orderDao.save(order);
    }

    public void addOrder(User user,
                         long inputPhoneNumber,
                         String inputDeliveryDate,
                         String inputPaymentMethod,
                         String inputName,
                         String inputSurname,
                         String inputCity,
                         int inputWarehouseNumber,
                         String inputOrderComment,
                         String[] field,
                         int[] qua,
                         Double inputWeight,
                         Double inputVolume) {
        Client newClient;
        PhoneNumber newPhoneNumber;
        Date orderDate = null;

        try {
            orderDate = new SimpleDateFormat("yyyy-MM-dd").parse(inputDeliveryDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (phoneNumberService.getPhoneNumber(inputPhoneNumber) == null) {
            newPhoneNumber = new PhoneNumber(inputPhoneNumber);
            newClient = new Client();
            newClient.setPhoneNumber(newPhoneNumber);
            newClient.setName(inputName);
            newClient.setSurname(inputSurname);
            newPhoneNumber.setClient(newClient);
            phoneNumberService.save(newPhoneNumber);
            clientService.save(newClient);
        }

        Order newOrder = new Order();

        newOrder.setDeliveryDate(orderDate);
        newOrder.setOrdersDate(new Date());

        switch (inputPaymentMethod) {
            case "Наложка":
                newOrder.setPaymentMethod(PaymentMethod.COD);
            case "Оценка":
                newOrder.setPaymentMethod(PaymentMethod.BANK_TRANSFER);
            case "Самовывоз":
                newOrder.setPaymentMethod(PaymentMethod.PICKUP);
        }



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

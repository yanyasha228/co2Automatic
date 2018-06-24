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
    private ProductService productService;
    @Autowired
    ClientService clientService;

    @Override
    public void save(Order order) {
        orderDao.save(order);
    }

    @Override
    public void addOrder(String inputPhoneNumber,
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


        if (!phoneNumberService.getPhoneNumberByPhoneNumber(inputPhoneNumber).isPresent()) {
            newPhoneNumber = new PhoneNumber(inputPhoneNumber);
            newClient = new Client();
            newClient.setPhoneNumber(newPhoneNumber);
            newClient.setName(inputName);
            newClient.setSurname(inputSurname);
            newPhoneNumber.setClient(newClient);
        } else {
            newPhoneNumber = phoneNumberService.getPhoneNumberByPhoneNumber(inputPhoneNumber).get();
            newClient = newPhoneNumber.getClient();
        }

        Order newOrder = new Order();
        newOrder.setClient(newClient);
        newOrder.setDeliveryDate(orderDate);
        newOrder.setOrdersDate(new Date());

        if (inputPaymentMethod.equalsIgnoreCase("наложка")) newOrder.setPaymentMethod(PaymentMethod.COD);
        if (inputPaymentMethod.equalsIgnoreCase("оценка")) newOrder.setPaymentMethod(PaymentMethod.BANK_TRANSFER);
        if (inputPaymentMethod.equalsIgnoreCase("самовывоз")) newOrder.setPaymentMethod(PaymentMethod.PICKUP);

        if (inputOrderComment != null) newOrder.setOrderComment(inputOrderComment);

        newOrder.setDeliveryPlace(inputCity);

        newOrder.setDeliveryPlaceWarehouseNumber(inputWarehouseNumber);

        newOrder.setOrderWeight(inputWeight);

        newOrder.setOrderVolumeGeneral(inputVolume);

        for (int i = 0; i < field.length; i++) {
            OrderLine newOrderLine = new OrderLine();
            Product prodForOrderLine = productService.findByProductName(field[i]).orElse(null);
            if (prodForOrderLine != null) {
                newOrderLine.setProduct(prodForOrderLine);
                newOrderLine.setAmount(qua[i]);
                newOrderLine.setPurchasePrice(newOrderLine.getProduct().getPrice() * newOrderLine.getAmount());
                newOrderLine.setOrder(newOrder);
                newOrder.getOrderLines().add(newOrderLine);
            }


        }

        save(newOrder);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.findAll();
//        return StreamSupport
//                .stream(
//                        Spliterators.spliteratorUnknownSize(orderDao.findAll().iterator(), Spliterator.NONNULL),
//                        false)
//                .collect(Collectors.toList());
    }
}

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
    public Optional<Order> findById(Long id) {
        return orderDao.findById(id);
    }

    /*
    * MiddleName ist involved!!!!
    * */

    @Override
    public void updateOrder(Long id,
                            String inputPhoneNumber,
                            String inputDeliveryDate,
                            String inputPaymentMethod,
                            String inputName,
                            String inputSurname,
                            String inputMiddleName,
                            String inputCity,
                            Integer inputWarehouseNumber,
                            String inputOrderComment,
                            Integer[] prodOrderLineIdInput,
                            Integer[] productQuaInput,
                            Double inputWeight,
                            Double inputVolume) {
        Client newClient;
        Date orderDate = null;

        try {
            orderDate = new SimpleDateFormat("yyyy-MM-dd").parse(inputDeliveryDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Optional<Client> clientFromDb = clientService.findClientByPhoneNumber(inputPhoneNumber);

        if (!clientFromDb.isPresent()) {
            newClient = new Client();
            newClient.setPhoneNumber(inputPhoneNumber);
            newClient.setName(inputName);
            newClient.setSurname(inputSurname);
        } else {
            newClient = clientFromDb.get();
            if (!newClient.getUsualDeliveryPlace().equalsIgnoreCase(inputCity))
                newClient.setUsualDeliveryPlace(inputCity);
            if (newClient.getUsualWarehouseNumber() == inputWarehouseNumber) {
            } else newClient.setUsualWarehouseNumber(inputWarehouseNumber);
        }

        Order newOrder = new Order();
        newOrder.setClient(newClient);
        newOrder.setDeliveryDate(orderDate);

        if (inputPaymentMethod.equalsIgnoreCase("наложка")) newOrder.setPaymentMethod(PaymentMethod.COD);
        if (inputPaymentMethod.equalsIgnoreCase("оценка")) newOrder.setPaymentMethod(PaymentMethod.BANK_TRANSFER);
        if (inputPaymentMethod.equalsIgnoreCase("самовывоз")) newOrder.setPaymentMethod(PaymentMethod.PICKUP);

        if (inputOrderComment != null) newOrder.setOrderComment(inputOrderComment);

        newOrder.setDeliveryPlace(inputCity);

        newOrder.setDeliveryPlaceWarehouseNumber(inputWarehouseNumber);

        newOrder.setOrderWeight(inputWeight);

        newOrder.setOrderVolumeGeneral(inputVolume);

        for (int i = 0; i < prodOrderLineIdInput.length; i++) {
            OrderLine newOrderLine = new OrderLine();
            Product prodForOrderLine = productService.findById(prodOrderLineIdInput[i]).orElse(null);
            if (prodForOrderLine != null) {
                newOrderLine.setProduct(prodForOrderLine);
                newOrderLine.setAmount(productQuaInput[i]);
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

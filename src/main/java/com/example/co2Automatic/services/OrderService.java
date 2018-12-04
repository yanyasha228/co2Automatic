package com.example.co2Automatic.services;

import com.example.co2Automatic.models.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    void save(Order order);

    Optional<Order> findById(Long id);

    void updateOrder(Long id,
                     String inputPhoneNumber,
                     String inputDeliveryDate,
                     String inputPaymentMethod,
                     String inputName,
                     String inputSurname,
                     String inputMiddleName,
                     String inputCity,
                     Integer inputWarehouseNumber,
                     String inputOrderComment,
                     String[] productNameInput,
                     Integer[] productQuaInput,
                     Double inputWeight,
                     Double inputVolume);

    List<Order> getAllOrders();
}

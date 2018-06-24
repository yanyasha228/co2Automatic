package com.example.co2Automatic.services;

import com.example.co2Automatic.models.Order;

import java.util.List;

public interface OrderService {
    void save(Order order);

    void addOrder(String inputPhoneNumber,
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
                  Double inputVolume);

    List<Order> getAllOrders();
}

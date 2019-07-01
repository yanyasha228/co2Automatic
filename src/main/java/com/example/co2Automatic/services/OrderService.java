package com.example.co2Automatic.services;

import com.example.co2Automatic.HelpUtils.CustomExceptions.ImpossibleEntityUpdatingException;
import com.example.co2Automatic.HelpUtils.CustomExceptions.ImpossibleSettingException;
import com.example.co2Automatic.HelpUtils.CustomExceptions.InsufficientAmountException;
import com.example.co2Automatic.models.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {


    Optional<Order> findById(Long id);

    Order save(Order order) throws InsufficientAmountException, ImpossibleSettingException;

    List<Order> save(List<Order> orders) throws InsufficientAmountException,
            ImpossibleSettingException,
            ImpossibleEntityUpdatingException;

    List<Order> update(List<Order> orders) throws InsufficientAmountException,
            ImpossibleSettingException,
            ImpossibleEntityUpdatingException;

    Order update(Order order) throws InsufficientAmountException, ImpossibleSettingException, ImpossibleEntityUpdatingException;
//    void update(Long id,
//                     Long clientId,
//                     String email,
//                     String inputPhoneNumber,
//                     String inputDeliveryDate,
//                     PaymentMethod inputPaymentMethod,
//                     String inputName,
//                     String inputSurname,
//                     String inputMiddleName,
//                     String inputCity,
//                     Integer inputWarehouseNumber,
//                     String inputOrderComment,
//                     Long[] prodOrderLineIdInput,
//                     Integer[] productQuaInput) throws InsufficientAmountException, ImpossibleSettingException;

    List<Order> findAll();

    void delete(Long id);

    void delete(Order order);

    void delete(List<Order> orders);
}

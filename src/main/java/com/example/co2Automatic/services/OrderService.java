package com.example.co2Automatic.services;

import com.example.co2Automatic.CustomExceptions.ImpossibleSettingException;
import com.example.co2Automatic.CustomExceptions.InsufficientAmountException;
import com.example.co2Automatic.models.Order;
import com.example.co2Automatic.models.OrderLine;
import com.example.co2Automatic.models.PaymentMethod;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    void save(Order order);

    Order saveEndReturnEntity(Order order);

    Optional<Order> findById(Long id);

    void updateOrder(Long id,
                     Long clientId,
                     String email,
                     String inputPhoneNumber,
                     String inputDeliveryDate,
                     PaymentMethod inputPaymentMethod,
                     String inputName,
                     String inputSurname,
                     String inputMiddleName,
                     String inputCity,
                     Integer inputWarehouseNumber,
                     String inputOrderComment,
                     Integer[] prodOrderLineIdInput,
                     Integer[] productQuaInput) throws InsufficientAmountException, ImpossibleSettingException;

    List<Order> getAllOrders();

    void deleteById(Long id);
}

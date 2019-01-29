package com.example.co2Automatic.services;

import com.example.co2Automatic.CustomExceptions.ImpossibleSettingException;
import com.example.co2Automatic.CustomExceptions.InsufficientAmountException;
import com.example.co2Automatic.Dao.OrderDao;
import com.example.co2Automatic.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private OrderLineService orderLineService;


    @Override
    public void save(Order order) {
        orderDao.save(order);
    }

    @Override
    public Order saveEndReturnEntity(Order order) {
        return orderDao.save(order);
    }


    @Override
    public Optional<Order> findById(Long id) {

        return orderDao.findById(id);
    }

    /*
     * MiddleName ist involved!!!!
     * */

    @Transactional
    @Override
    public void updateOrder(Long id,
                            Long clientId,
                            String inputEmail,
                            String inputPhoneNumber,
                            String inputDeliveryDate,
                            PaymentMethod inputPaymentMethod,
                            String inputName,
                            String inputLastName,
                            String inputMiddleName,
                            String inputCity,
                            Integer inputWarehouseNumber,
                            String inputOrderComment,
                            Integer[] prodOrderLineIdInput,
                            Integer[] productQuaInput) throws InsufficientAmountException, ImpossibleSettingException {


        Optional<Client> clientFromDb = clientService.findById(clientId);

        Optional<Order> orderOpt = findById(id);

        Order order;

        Client orderClient;

        Date orderDate = null;

        try {
            orderDate = new SimpleDateFormat("yyyy-MM-dd").parse(inputDeliveryDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (clientFromDb.isPresent()) {
            orderClient = clientFromDb.get();
        } else {
            orderClient = new Client();
            orderClient.setEmail(inputEmail);
            orderClient.setPhoneNumber(inputPhoneNumber);
            orderClient.setName(inputName);
            orderClient.setLastName(inputLastName);
            orderClient.setMiddleName(inputMiddleName);
            orderClient.setUsualDeliveryPlace(inputCity);
            orderClient.setUsualWarehouseNumber(inputWarehouseNumber);
            orderClient.setClientStatus(ClientStatus.USUAL);

            orderClient = clientService.saveAndReturnEntity(orderClient);

        }

        order = orderOpt.orElseGet(Order::new);

        order.setDeliveryPlace(inputCity);
        order.setDeliveryDate(orderDate);
        order.setDeliveryPlaceWarehouseNumber(inputWarehouseNumber);
        order.setPaymentMethod(inputPaymentMethod);
        order.setOrderComment(inputOrderComment);


        Set<OrderLine> orderLines = new HashSet<OrderLine>();


        for (int i = 0; i < prodOrderLineIdInput.length; i++) {
            OrderLine newOrderLine = new OrderLine();
            Product prodForOrderLine = productService.findById(prodOrderLineIdInput[i]).orElse(null);
            if (prodForOrderLine != null) {
                newOrderLine.setProductWithAmount(prodForOrderLine, productQuaInput[i]);

                if (orderClient.getClientStatus() == ClientStatus.WHOLESALER) {
                    newOrderLine.setPurchasePrice(newOrderLine.getProduct().getWholeSalePrice() * newOrderLine.getAmount());
                } else {
                    newOrderLine.setPurchasePrice(newOrderLine.getProduct().getPrice() * newOrderLine.getAmount());
                }
                orderLines.add(newOrderLine);

            }


        }


        order.setOrderLines(orderLines);

        order.setClient(orderClient);

        order = saveEndReturnEntity(order);

        int i = 7;

//        Client newClient;
//        Date orderDate = null;
//
//        try {
//            orderDate = new SimpleDateFormat("yyyy-MM-dd").parse(inputDeliveryDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        Optional<Client> clientFromDb = clientService.findById(clientId);
//
//        if (!clientFromDb.isPresent()) {
//            newClient = new Client();
//            newClient.setPhoneNumber(inputPhoneNumber);
//            newClient.setName(inputName);
//            newClient.setLastName(inputSurname);
//        } else {
//            newClient = clientFromDb.get();
//            if (!newClient.getUsualDeliveryPlace().equalsIgnoreCase(inputCity))
//                newClient.setUsualDeliveryPlace(inputCity);
//            if (newClient.getUsualWarehouseNumber() == inputWarehouseNumber) {
//            } else newClient.setUsualWarehouseNumber(inputWarehouseNumber);
//        }
//
//        Order newOrder = new Order();
//        newOrder.setClient(newClient);
//        newOrder.setDeliveryDate(orderDate);
//
//        if (inputPaymentMethod.equalsIgnoreCase("наложка")) newOrder.setPaymentMethod(PaymentMethod.COD);
//        if (inputPaymentMethod.equalsIgnoreCase("оценка")) newOrder.setPaymentMethod(PaymentMethod.BANK_TRANSFER);
//        if (inputPaymentMethod.equalsIgnoreCase("самовывоз")) newOrder.setPaymentMethod(PaymentMethod.PICKUP);
//
//        if (inputOrderComment != null) newOrder.setOrderComment(inputOrderComment);
//
//        newOrder.setDeliveryPlace(inputCity);
//
//        newOrder.setDeliveryPlaceWarehouseNumber(inputWarehouseNumber);
//
//        newOrder.setOrderWeight(inputWeight);
//
//        newOrder.setOrderVolumeGeneral(inputVolume);
//
//        for (int i = 0; i < prodOrderLineIdInput.length; i++) {
//            OrderLine newOrderLine = new OrderLine();
//            Product prodForOrderLine = productService.findById(prodOrderLineIdInput[i]).orElse(null);
//            if (prodForOrderLine != null) {
//                newOrderLine.setProduct(prodForOrderLine);
//                newOrderLine.setAmount(productQuaInput[i]);
//                newOrderLine.setPurchasePrice(newOrderLine.getProduct().getPrice() * newOrderLine.getAmount());
//                newOrderLine.setOrder(newOrder);
//                newOrder.getOrderLines().add(newOrderLine);
//            }
//
//
//        }
//
//        save(newOrder);
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

    @Override
    public void deleteById(Long id) {
        orderDao.deleteById(id);
    }
}

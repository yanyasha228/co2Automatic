package com.example.co2Automatic.services;

import com.example.co2Automatic.HelpUtils.CustomExceptions.ImpossibleEntityUpdatingException;
import com.example.co2Automatic.HelpUtils.CustomExceptions.ImpossibleSettingException;
import com.example.co2Automatic.HelpUtils.CustomExceptions.InsufficientAmountException;
import com.example.co2Automatic.Dao.OrderDao;
import com.example.co2Automatic.models.*;
import com.example.co2Automatic.models.ModelEnums.ClientStatus;
import com.example.co2Automatic.models.ModelEnums.OrderStatus;
import com.example.co2Automatic.models.ModelEnums.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
    public Optional<Order> findById(Long id) {

        return orderDao.findById(id);
    }

    @Transactional
    @Override
    public Order save(Order order) throws InsufficientAmountException, ImpossibleSettingException {

        if (!clientService.exists(order.getClient())){
            clientService.save(order.getClient());
        }
            return orderDao.save(order);
    }

    @Override
    public List<Order> save(List<Order> orders) throws InsufficientAmountException, ImpossibleSettingException, ImpossibleEntityUpdatingException {
        return orderDao.saveAll(orders);
    }

    @Override
    public List<Order> update(List<Order> orders) throws InsufficientAmountException, ImpossibleSettingException, ImpossibleEntityUpdatingException {
        for (Order oneOf : orders) {
            if (oneOf.getId() <= 0)
                throw new ImpossibleEntityUpdatingException("Attempt to update entity without ID!!!");
        }
        return orderDao.saveAll(orders);
    }


    public Order update(Order order) throws InsufficientAmountException,
            ImpossibleSettingException,
            ImpossibleEntityUpdatingException {
        if (order.getId() == 0)
            throw new ImpossibleEntityUpdatingException("Attempt to update entity without ID!!!");

        return orderDao.save(order);

    }


    /*
     * MiddleName ist involved!!!!
     *
     */
    //    Transactional ???????????????????????????????
    @Transactional
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
                            Long[] prodOrderLineIdInput,
                            Integer[] productQuaInput) throws InsufficientAmountException, ImpossibleSettingException {


        Optional<Client> clientFromDb = clientService.findById(clientId);

        Optional<Order> orderOpt = findById(id);

        Order order;

        Client orderClient;

        LocalDate orderDate = null;


        orderDate = LocalDate.parse(inputDeliveryDate);
//                    new SimpleDateFormat("yyyy-MM-dd").parse(inputDeliveryDate);


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
            orderClient = clientService.save(orderClient);

        }

        order = orderOpt.orElseGet(Order::new);

        order.setDeliveryPlace(inputCity);
        order.setDeliveryDate(orderDate);
        order.setDeliveryPlaceWarehouseNumber(inputWarehouseNumber);
        order.setPaymentMethod(inputPaymentMethod);
        order.setOrderComment(inputOrderComment);
        order.setOrderStatus(OrderStatus.CONFIRMED);


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

        save(order);


    }

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
//        return StreamSupport
//                .stream(
//                        Spliterators.spliteratorUnknownSize(orderDao.findAll().iterator(), Spliterator.NONNULL),
//                        false)
//                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        orderDao.deleteById(id);
    }

    @Override
    public void delete(Order order) {
        orderDao.delete(order);
    }

    @Override
    public void delete(List<Order> orders) {

        orderDao.deleteAll(orders);
    }
}

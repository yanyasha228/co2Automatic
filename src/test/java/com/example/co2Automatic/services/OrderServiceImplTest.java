package com.example.co2Automatic.services;

import com.example.co2Automatic.HelpUtils.CustomExceptions.ImpossibleSettingException;
import com.example.co2Automatic.HelpUtils.CustomExceptions.InsufficientAmountException;
import com.example.co2Automatic.models.*;
import com.example.co2Automatic.models.ModelEnums.ClientStatus;
import com.example.co2Automatic.models.ModelEnums.MoneyCurrency;
import com.example.co2Automatic.models.ModelEnums.PaymentMethod;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private OrderLineService orderLineService;

    private List<Long> prodId = new ArrayList<>();
    private List<Long> clientId = new ArrayList<>();

    @Before
    public void setTestData(){
        Product newProd = new Product();
        newProd.setName("ProdName");
        newProd.setCurrency(MoneyCurrency.UAH);
        newProd.setCountryOfOrigin("USA");
        newProd.setDescription("ProdDescription");
        newProd.setQuantity(10);

        Product newProd1 = new Product();
        newProd1.setName("ProdName");
        newProd1.setCurrency(MoneyCurrency.UAH);
        newProd1.setCountryOfOrigin("USA");
        newProd1.setDescription("ProdDescription");
        newProd1.setQuantity(10);

        prodId.add(productService.save(newProd).getId());
        prodId.add(productService.save(newProd1).getId());

        Client newClient = new Client();
        newClient.setPhoneNumber("+380665025671");
        newClient.setName("Kucenko");
        newClient.setLastName("Nikolai");
        newClient.setMiddleName("Vasilievich");
        newClient.setEmail("kucahuca@bin.com");
        newClient.setUsualDeliveryPlace("Kiev");
        newClient.setUsualWarehouseNumber(87);
        newClient.setClientStatus(ClientStatus.CONSTANT);

        Client orderClient = new Client();
        orderClient.setEmail("igor777@gmail.com");
        orderClient.setPhoneNumber("380660001507");
        orderClient.setName("Igor");
        orderClient.setLastName("Seleverstov");
        orderClient.setMiddleName("Vasilevich");
        orderClient.setUsualDeliveryPlace("Kiev");
        orderClient.setUsualWarehouseNumber(25);
        orderClient.setClientStatus(ClientStatus.USUAL);

        clientId.add(clientService.save(newClient).getId());
        clientId.add(clientService.save(orderClient).getId());

    }


    @Test
    public void addOrder() throws ImpossibleSettingException, InsufficientAmountException {

        Long[] iarr = new Long[prodId.size()];
        iarr = prodId.toArray(iarr);
        orderService.updateOrder(0L,
                0L,
                "igor777@gmail.com",
                "380660001507",
                "2019-02-05",
                PaymentMethod.COD,
                "Igor",
                "Seleverstov",
                "Vasilevich",
                "Kiev",
                57,"Pervii Zakaz",
                 iarr,
                new Integer[]{2,3});

        List<Order> ordFrDb = orderService.findAll();

        assertTrue(ordFrDb.stream()
                .anyMatch(order -> order.getOrderComment().equalsIgnoreCase("Pervii Zakaz")));

    }


}
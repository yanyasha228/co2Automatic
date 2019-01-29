package com.example.co2Automatic.services;

import com.example.co2Automatic.Dao.OrderDao;
import com.example.co2Automatic.Dao.OrderLineDao;
import com.example.co2Automatic.Dao.ProductDao;
import com.example.co2Automatic.models.Client;
import com.example.co2Automatic.models.ClientStatus;
import com.example.co2Automatic.models.MoneyCurrency;
import com.example.co2Automatic.models.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

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

    private Long prodId;
    private Long clientId;

    @Before
    public void setTestData(){
        Product newProd = new Product();
        newProd.setName("ProdName");
        newProd.setCurrency(MoneyCurrency.UAH);
        newProd.setCountryOfOrigin("USA");
        newProd.setDescription("ProdDescription");

        prodId = productService.saveAndReturnEntity(newProd).getId();


        Client newClient = new Client();
        newClient.setPhoneNumber("+380665025671");
        newClient.setName("Kucenko");
        newClient.setLastName("Nikolai");
        newClient.setMiddleName("Vasilievich");
        newClient.setEmail("kucahuca@bin.com");
        newClient.setUsualDeliveryPlace("Kiev");
        newClient.setUsualWarehouseNumber(87);
        newClient.setClientStatus(ClientStatus.CONSTANT);

        clientId = clientService.saveAndReturnEntity(newClient).getId();

    }

    @Test
    public void addOrder() {


    }


}
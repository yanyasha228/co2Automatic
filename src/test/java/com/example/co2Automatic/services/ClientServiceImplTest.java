package com.example.co2Automatic.services;

import com.example.co2Automatic.models.Client;
import com.example.co2Automatic.models.ModelEnums.ClientStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceImplTest {

    @Autowired
    private ClientService clientService;

    @Test
    public void addClient(){

        String newClientPhoneNumber = "+380665025671";

        Client testClient13 = new Client();
        testClient13.setPhoneNumber(newClientPhoneNumber);
        testClient13.setName("Kucenko");
        testClient13.setLastName("Nikolai");
        testClient13.setMiddleName("Vasilievich");
        testClient13.setEmail("kucahuca@bin.com");
        testClient13.setUsualDeliveryPlace("Kiev");
        testClient13.setUsualWarehouseNumber(87);
        testClient13.setClientStatus(ClientStatus.CONSTANT);

       clientService.save(testClient13);
//        Client clientFromDb = clientService.save(testClient13);

        Client clientFromDb = clientService.findClientByPhoneNumber(newClientPhoneNumber).orElse(null);
        Assert.assertNotNull(clientFromDb);

    }
}
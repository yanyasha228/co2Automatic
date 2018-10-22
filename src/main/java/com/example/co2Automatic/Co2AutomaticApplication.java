package com.example.co2Automatic;

import com.example.co2Automatic.models.*;
import com.example.co2Automatic.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Co2AutomaticApplication {
    private static final Logger log = LoggerFactory.getLogger(Co2AutomaticApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(Co2AutomaticApplication.class, args);
    }

    @Bean("CRunner")
    public CommandLineRunner testDataProd(PhoneNumberService phoneNumberService,
                                          ClientService clientService) {
        return (args) -> {
            // save a couple of customers

            List<Client> clientListFromDao = clientService.findAll();

            List<PhoneNumber> phoneNumberList = new ArrayList<>();

            List<Client> clientList = new ArrayList<>();


                PhoneNumber testPhoneNumber = new PhoneNumber();
            testPhoneNumber.setPhoneNumber("0660001500");
            phoneNumberList.add(testPhoneNumber);

            PhoneNumber testPhoneNumber1 = new PhoneNumber();
            testPhoneNumber1.setPhoneNumber("0660001501");
            phoneNumberList.add(testPhoneNumber1);

            PhoneNumber testPhoneNumber2 = new PhoneNumber();
            testPhoneNumber2.setPhoneNumber("0660001502");
            phoneNumberList.add(testPhoneNumber2);

            PhoneNumber testPhoneNumber3 = new PhoneNumber();
            testPhoneNumber3.setPhoneNumber("0660001503");
            phoneNumberList.add(testPhoneNumber3);

            PhoneNumber testPhoneNumber4 = new PhoneNumber();
            testPhoneNumber4.setPhoneNumber("0660001504");
            phoneNumberList.add(testPhoneNumber4);

            PhoneNumber testPhoneNumber5 = new PhoneNumber();
            testPhoneNumber5.setPhoneNumber("0660001505");
            phoneNumberList.add(testPhoneNumber5);

            PhoneNumber testPhoneNumber6 = new PhoneNumber();
            testPhoneNumber6.setPhoneNumber("0660001506");
            phoneNumberList.add(testPhoneNumber6);

            PhoneNumber testPhoneNumber7 = new PhoneNumber();
            testPhoneNumber7.setPhoneNumber("0660001507");
            phoneNumberList.add(testPhoneNumber7);

            PhoneNumber testPhoneNumber8 = new PhoneNumber();
            testPhoneNumber8.setPhoneNumber("0660001508");
            phoneNumberList.add(testPhoneNumber8);

            PhoneNumber testPhoneNumber9 = new PhoneNumber();
            testPhoneNumber9.setPhoneNumber("0660001509");
            phoneNumberList.add(testPhoneNumber9);

            PhoneNumber testPhoneNumber10 = new PhoneNumber();
            testPhoneNumber10.setPhoneNumber("0660001510");
            phoneNumberList.add(testPhoneNumber10);

            PhoneNumber testPhoneNumber11 = new PhoneNumber();
            testPhoneNumber11.setPhoneNumber("0660001511");
            phoneNumberList.add(testPhoneNumber11);

            PhoneNumber testPhoneNumber12 = new PhoneNumber();
            testPhoneNumber12.setPhoneNumber("0660001512");
            phoneNumberList.add(testPhoneNumber12);

            PhoneNumber testPhoneNumber13 = new PhoneNumber();
            testPhoneNumber13.setPhoneNumber("0660001513");
            phoneNumberList.add(testPhoneNumber13);

            /////////////////////////////////////////////////////////////////////////////

            Client testClient = new Client();
            testClient.setPhoneNumber(testPhoneNumber);
            testClient.setName("Vasiliy");
            testClient.setSurname("Pupkin");
            testClient.setPatronymic("Kaloshin");
            testClient.setEmail("piska@gmail.com");
            testClient.setUsualDeliveryPlace("Kiyv");
            testClient.setUsualWarehouseNumber(23);
            testClient.setClientStatus(ClientStatus.CONSTANT);
            clientList.add(testClient);

            Client testClient1 = new Client();
            testClient1.setPhoneNumber(testPhoneNumber1);
            testClient1.setName("Kapa");
            testClient1.setSurname("Pupkina");
            testClient1.setPatronymic("hopshin");
            testClient1.setEmail("padsasa@gmail.com");
            testClient1.setUsualDeliveryPlace("Kharkov");
            testClient1.setUsualWarehouseNumber(22);
            testClient1.setClientStatus(ClientStatus.USUAL);
            clientList.add(testClient1);

            Client testClient2 = new Client();
            testClient2.setPhoneNumber(testPhoneNumber2);
            testClient2.setName("Egor");
            testClient2.setSurname("Pustota");
            testClient2.setPatronymic("Lipsin");
            testClient2.setEmail("drocha@gmail.com");
            testClient2.setUsualDeliveryPlace("Zalupovka");
            testClient2.setUsualWarehouseNumber(1);
            testClient2.setClientStatus(ClientStatus.WHOLESALER);
            clientList.add(testClient2);

            Client testClient3 = new Client();
            testClient3.setPhoneNumber(testPhoneNumber3);
            testClient3.setName("Pisulya");
            testClient3.setSurname("Dmitriev");
            testClient3.setPatronymic("Davidovich");
            testClient3.setEmail("turka@gmail.com");
            testClient3.setUsualDeliveryPlace("Sran");
            testClient3.setUsualWarehouseNumber(89);
            testClient3.setClientStatus(ClientStatus.CONSTANT);
            clientList.add(testClient3);


            Client testClient4 = new Client();
            testClient4.setPhoneNumber(testPhoneNumber4);
            testClient4.setName("Oleg");
            testClient4.setSurname("Smola");
            testClient4.setPatronymic("Genadievich");
            testClient4.setEmail("torch@mail.com");
            testClient4.setUsualDeliveryPlace("Mezhova");
            testClient4.setUsualWarehouseNumber(45);
            testClient4.setClientStatus(ClientStatus.CONSTANT);
            clientList.add(testClient4);

            Client testClient5 = new Client();
            testClient5.setPhoneNumber(testPhoneNumber5);
            testClient5.setName("Igor");
            testClient5.setSurname("Kulibin");
            testClient5.setPatronymic("Hutorov");
            testClient5.setEmail("pppp@gmail.com");
            testClient5.setUsualDeliveryPlace("Kurahovo");
            testClient5.setUsualWarehouseNumber(5);
            testClient5.setClientStatus(ClientStatus.USUAL);
            clientList.add(testClient5);


            Client testClient6 = new Client();
            testClient6.setPhoneNumber(testPhoneNumber6);
            testClient6.setName("Dura");
            testClient6.setSurname("Ivanovna");
            testClient6.setPatronymic("Kuryakova");
            testClient6.setEmail("kuryakova777@gmail.com");
            testClient6.setUsualDeliveryPlace("Kerch");
            testClient6.setUsualWarehouseNumber(2);
            testClient6.setClientStatus(ClientStatus.WHOLESALER);
            clientList.add(testClient6);

            Client testClient7 = new Client();
            testClient7.setPhoneNumber(testPhoneNumber7);
            testClient7.setName("Abdula");
            testClient7.setSurname("Zarubin");
            testClient7.setPatronymic("Myasov");
            testClient7.setEmail("azm@gmail.com");
            testClient7.setUsualDeliveryPlace("Kiyv");
            testClient7.setUsualWarehouseNumber(78);
            testClient7.setClientStatus(ClientStatus.BAN);
            clientList.add(testClient7);


            Client testClient8 = new Client();
            testClient8.setPhoneNumber(testPhoneNumber8);
            testClient8.setName("Kurka");
            testClient8.setSurname("Churka");
            testClient8.setPatronymic("Piskinin");
            testClient8.setEmail("kchp7777@gmail.com");
            testClient8.setUsualDeliveryPlace("Ilichinsk");
            testClient8.setUsualWarehouseNumber(34);
            testClient8.setClientStatus(ClientStatus.BAN);
            clientList.add(testClient8);


            Client testClient9 = new Client();
            testClient9.setPhoneNumber(testPhoneNumber9);
            testClient9.setName("Yan");
            testClient9.setSurname("Kravchenko");
            testClient9.setPatronymic("Vaskin");
            testClient9.setEmail("tupoloh228@gmail.com");
            testClient9.setUsualDeliveryPlace("Dnepr");
            testClient9.setUsualWarehouseNumber(57);
            testClient9.setClientStatus(ClientStatus.CONSTANT);
            clientList.add(testClient9);


            Client testClient10 = new Client();
            testClient10.setPhoneNumber(testPhoneNumber10);
            testClient10.setName("Krol");
            testClient10.setSurname("Huiol");
            testClient10.setPatronymic("Aerozol");
            testClient10.setEmail("67aerozol67@gmail.com");
            testClient10.setUsualDeliveryPlace("Azov");
            testClient10.setUsualWarehouseNumber(2);
            testClient10.setClientStatus(ClientStatus.USUAL);
            clientList.add(testClient10);


            Client testClient11 = new Client();
            testClient11.setPhoneNumber(testPhoneNumber11);
            testClient11.setName("Vasiliy");
            testClient11.setSurname("Selever");
            testClient11.setPatronymic("Vladislavovich");
            testClient11.setEmail("vladikhuiadik@gmail.com");
            testClient11.setUsualDeliveryPlace("Zachepilovka");
            testClient11.setUsualWarehouseNumber(567);
            testClient11.setClientStatus(ClientStatus.WHOLESALER);
            clientList.add(testClient11);


            Client testClient12 = new Client();
            testClient12.setPhoneNumber(testPhoneNumber12);
            testClient12.setName("Dima");
            testClient12.setSurname("Kolyadenko");
            testClient12.setPatronymic("Hutorok");
            testClient12.setEmail("yahutor@gmail.com");
            testClient12.setUsualDeliveryPlace("Krasnoarmeysk");
            testClient12.setUsualWarehouseNumber(99);
            testClient12.setClientStatus(ClientStatus.CONSTANT);
            clientList.add(testClient12);


            Client testClient13 = new Client();
            testClient13.setPhoneNumber(testPhoneNumber13);
            testClient13.setName("Andrey");
            testClient13.setSurname("Kulibin");
            testClient13.setPatronymic("Ulukin");
            testClient13.setEmail("urka@bin.com");
            testClient13.setUsualDeliveryPlace("Srakovka");
            testClient13.setUsualWarehouseNumber(1);
            testClient13.setClientStatus(ClientStatus.BAN);
            clientList.add(testClient13);

//            for(PhoneNumber phoneNumberFS : phoneNumberList){
//                phoneNumberService.save(phoneNumberFS);
//            }
            for (Client clientFS : clientList) {

                clientService.save(clientFS);
            }


            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Client cProd : clientService.findAll()) {
                log.info(cProd.toString());
                log.info(cProd.getPhoneNumber().getPhoneNumber());
            }
            log.info("");

            // fetch an individual customer by ID
            clientService.findById(1L)
                    .ifPresent(cProd -> {
                        log.info("Customer found with findById(1L):");
                        log.info("--------------------------------");
                        log.info(cProd.toString());
                        log.info("");
                    });

            // fetch customers by last name
//            log.info("Customer found with findByLastName('Bauer'):");
//            log.info("--------------------------------------------");
//            productService.findByProductName("Hatsan 125 TH").ifPresent(prodBN -> {
//                log.info(prodBN.toString());
//            });
//            // for (Customer bauer : repository.findByLastName("Bauer")) {
            // 	log.info(bauer.toString());
            // }
            log.info("");
        };
    }

}

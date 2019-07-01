package com.example.co2Automatic;

import com.example.co2Automatic.models.*;
import com.example.co2Automatic.models.ModelEnums.ClientStatus;
import com.example.co2Automatic.models.ModelEnums.ProductStock;
import com.example.co2Automatic.models.SessionModels.AppSettingsModel;
import com.example.co2Automatic.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
    public CommandLineRunner testDataProd(ClientService clientService,
                                          ProductService productService,
                                          AppSettingsModelService appSettingsModelService,
                                          OrderService orderService) {
        return (args) -> {
            // save a couple of customers

            List<Product> productList = productService.findAll();

            for (Product pr: productList) {
                pr.setProductStock(ProductStock.CO2_STOCK);
                pr.setQuantity(10);
            }

          productService.save(productList);

            List<Client> clientListFromDao = clientService.findAll();

            List<PhoneNumber> phoneNumberList = new ArrayList<>();

            List<Client> clientList = new ArrayList<>();


            if(!appSettingsModelService.getSettings().isPresent()){
                AppSettingsModel appSettingsModel = new AppSettingsModel();

                appSettingsModel.setId(1L);
                appSettingsModel.setEurCurrency(31.5);
                appSettingsModel.setUsdCurrency(26.4);

                appSettingsModelService.update(appSettingsModel);
            }





            /////////////////////////////////////////////////////////////////////////////

            if(clientListFromDao.isEmpty()) {
                Client testClient = new Client();
                testClient.setPhoneNumber("+380660001500");
                testClient.setName("Vasiliy");
                testClient.setLastName("Pupkin");
                testClient.setMiddleName("Kaloshin");
                testClient.setEmail("piska@gmail.com");
                testClient.setUsualDeliveryPlace("Kiyv");
                testClient.setUsualWarehouseNumber(23);
                testClient.setClientStatus(ClientStatus.CONSTANT);
                clientList.add(testClient);

                Client testClient1 = new Client();
                testClient1.setPhoneNumber("+380660001501");
                testClient1.setName("Kapa");
                testClient1.setLastName("Pupkina");
                testClient1.setMiddleName("hopshin");
                testClient1.setEmail("padsasa@gmail.com");
                testClient1.setUsualDeliveryPlace("Kharkov");
                testClient1.setUsualWarehouseNumber(22);
                testClient1.setClientStatus(ClientStatus.USUAL);
                clientList.add(testClient1);

                Client testClient2 = new Client();
                testClient2.setPhoneNumber("+380660001502");
                testClient2.setName("Egor");
                testClient2.setLastName("Pustota");
                testClient2.setMiddleName("Lipsin");
                testClient2.setEmail("drocha@gmail.com");
                testClient2.setUsualDeliveryPlace("Zalupovka");
                testClient2.setUsualWarehouseNumber(1);
                testClient2.setClientStatus(ClientStatus.WHOLESALER);
                clientList.add(testClient2);

                Client testClient3 = new Client();
                testClient3.setPhoneNumber("+380660001503");
                testClient3.setName("Pisulya");
                testClient3.setLastName("Dmitriev");
                testClient3.setMiddleName("Davidovich");
                testClient3.setEmail("turka@gmail.com");
                testClient3.setUsualDeliveryPlace("Sran");
                testClient3.setUsualWarehouseNumber(89);
                testClient3.setClientStatus(ClientStatus.CONSTANT);
                clientList.add(testClient3);


                Client testClient4 = new Client();
                testClient4.setPhoneNumber("+380660001504");
                testClient4.setName("Oleg");
                testClient4.setLastName("Smola");
                testClient4.setMiddleName("Genadievich");
                testClient4.setEmail("torch@mail.com");
                testClient4.setUsualDeliveryPlace("Mezhova");
                testClient4.setUsualWarehouseNumber(45);
                testClient4.setClientStatus(ClientStatus.CONSTANT);
                clientList.add(testClient4);

                Client testClient5 = new Client();
                testClient5.setPhoneNumber("+380660001505");
                testClient5.setName("Igor");
                testClient5.setLastName("Kulibin");
                testClient5.setMiddleName("Hutorov");
                testClient5.setEmail("pppp@gmail.com");
                testClient5.setUsualDeliveryPlace("Kurahovo");
                testClient5.setUsualWarehouseNumber(5);
                testClient5.setClientStatus(ClientStatus.USUAL);
                clientList.add(testClient5);


                Client testClient6 = new Client();
                testClient6.setPhoneNumber("+380660001506");
                testClient6.setName("Dura");
                testClient6.setLastName("Ivanovna");
                testClient6.setMiddleName("Kuryakova");
                testClient6.setEmail("kuryakova777@gmail.com");
                testClient6.setUsualDeliveryPlace("Kerch");
                testClient6.setUsualWarehouseNumber(2);
                testClient6.setClientStatus(ClientStatus.WHOLESALER);
                clientList.add(testClient6);

                Client testClient7 = new Client();
                testClient7.setPhoneNumber("+380660001507");
                testClient7.setName("Abdula");
                testClient7.setLastName("Zarubin");
                testClient7.setMiddleName("Myasov");
                testClient7.setEmail("azm@gmail.com");
                testClient7.setUsualDeliveryPlace("Kiyv");
                testClient7.setUsualWarehouseNumber(78);
                testClient7.setClientStatus(ClientStatus.BAN);
                clientList.add(testClient7);


                Client testClient8 = new Client();
                testClient8.setPhoneNumber("+380660001508");
                testClient8.setName("Kurka");
                testClient8.setLastName("Churka");
                testClient8.setMiddleName("Piskinin");
                testClient8.setEmail("kchp7777@gmail.com");
                testClient8.setUsualDeliveryPlace("Ilichinsk");
                testClient8.setUsualWarehouseNumber(34);
                testClient8.setClientStatus(ClientStatus.BAN);
                clientList.add(testClient8);


                Client testClient9 = new Client();
                testClient9.setPhoneNumber("+380660001510");
                testClient9.setName("Yan");
                testClient9.setLastName("Kravchenko");
                testClient9.setMiddleName("Vaskin");
                testClient9.setEmail("tupoloh228@gmail.com");
                testClient9.setUsualDeliveryPlace("Dnepr");
                testClient9.setUsualWarehouseNumber(57);
                testClient9.setClientStatus(ClientStatus.CONSTANT);
                clientList.add(testClient9);


                Client testClient10 = new Client();
                testClient10.setPhoneNumber("+380660001511");
                testClient10.setName("Krol");
                testClient10.setLastName("Huiol");
                testClient10.setMiddleName("Aerozol");
                testClient10.setEmail("67aerozol67@gmail.com");
                testClient10.setUsualDeliveryPlace("Azov");
                testClient10.setUsualWarehouseNumber(2);
                testClient10.setClientStatus(ClientStatus.USUAL);
                clientList.add(testClient10);


                Client testClient11 = new Client();
                testClient11.setPhoneNumber("+380660001512");
                testClient11.setName("Vasiliy");
                testClient11.setLastName("Selever");
                testClient11.setMiddleName("Vladislavovich");
                testClient11.setEmail("vladikhuiadik@gmail.com");
                testClient11.setUsualDeliveryPlace("Zachepilovka");
                testClient11.setUsualWarehouseNumber(567);
                testClient11.setClientStatus(ClientStatus.WHOLESALER);
                clientList.add(testClient11);


                Client testClient12 = new Client();
                testClient12.setPhoneNumber("+380660001513");
                testClient12.setName("Dima");
                testClient12.setLastName("Kolyadenko");
                testClient12.setMiddleName("Hutorok");
                testClient12.setEmail("yahutor@gmail.com");
                testClient12.setUsualDeliveryPlace("Krasnoarmeysk");
                testClient12.setUsualWarehouseNumber(99);
                testClient12.setClientStatus(ClientStatus.CONSTANT);
                clientList.add(testClient12);


                Client testClient13 = new Client();
                testClient13.setPhoneNumber("+380660001513");
                testClient13.setName("Andrey");
                testClient13.setLastName("Kulibin");
                testClient13.setMiddleName("Ulukin");
                testClient13.setEmail("urka@bin.com");
                testClient13.setUsualDeliveryPlace("Srakovka");
                testClient13.setUsualWarehouseNumber(1);
                testClient13.setClientStatus(ClientStatus.BAN);
                clientList.add(testClient13);

                Order order = new Order();
                order.setClient(testClient);

//            for(PhoneNumber phoneNumberFS : phoneNumberList){
//                phoneNumberService.save(phoneNumberFS);
//            }
                for (Client clientFS : clientList) {

                    clientService.save(clientFS);
                }

//            List<Client> clientsFDB = clientService.findAll();
//
//            // fetch all customers
//            log.info("Customers found with findAll():");
//            log.info("-------------------------------");
//            for (Client cProd : clientsFDB) {
//                log.info(cProd.toString());
//                log.info(cProd.getPhoneNumber().getPhoneNumber());
//            }
//            log.info("");
//
//            // fetch an individual customer by ID
//            clientService.findById(1L)
//                    .ifPresent(cProd -> {
//                        log.info("Customer found with findById(1L):");
//                        log.info("--------------------------------");
//                        log.info(cProd.toString());
//                        log.info("");
//                    });
//
//            // fetch customers by last name
////            log.info("Customer found with findByLastName('Bauer'):");
////            log.info("--------------------------------------------");
////            productService.findByProductName("Hatsan 125 TH").ifPresent(prodBN -> {
////                log.info(prodBN.toString());
////            });
////            // for (Customer bauer : repository.findByLastName("Bauer")) {
//            // 	log.info(bauer.toString());
//            // }
//            log.info("");
            }
        };
    }

}

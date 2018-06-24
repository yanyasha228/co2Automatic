package com.example.co2Automatic;

import com.example.co2Automatic.models.*;
import com.example.co2Automatic.services.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Co2AutomaticApplication {
    private static final Logger log = LoggerFactory.getLogger(Co2AutomaticApplication.class);
    public static void main(String[] args) {

        SpringApplication.run(Co2AutomaticApplication.class, args);


//
////Test Products
//

//        Product product = new Product();
//        product.setPrice(25.67);
//        product.setDescription("Pnevmaticheskaya Vintowka");
//        product.setName("Hatsan 125 TH");
//        product.setQuantity(67);
//        product.setWholesalePrice(21.01);
//        product.getOrderLines().add(new OrderLine());
//
//        Product product1 = new Product();
//        product1.setPrice(100.26);
//        product1.setDescription("CO2 Pistolet");
//        product1.setName("KM44");
//        product1.setQuantity(12);
//        product1.setWholesalePrice(75.89);
//        product1.getOrderLines().add(new OrderLine());
//
//        Product product2 = new Product();
//        product2.setPrice(57.33);
//        product2.setDescription("CO2 Pistolet");
//        product2.setName("Osevoy Vint Hatsan Striker Edge");
//        product2.setQuantity(53);
//        product2.setWholesalePrice(7.67);
//        product2.getOrderLines().add(new OrderLine());
//
//        productService.save(product);
//        productService.save(product1);
//        productService.save(product2);

////Test OrderLines
//
//        OrderLine orderLine = new OrderLine();
//        orderLine.setProduct(product);
//        orderLine.setAmount(3);
//        orderLine.setPurchasePrice((product.getPrice() * orderLine.getAmount()));
//
//        OrderLine orderLine1 = new OrderLine();
//        orderLine1.setProduct(product1);
//        orderLine1.setAmount(7);
//        orderLine1.setPurchasePrice((product1.getPrice() * orderLine1.getAmount()));
//
//        OrderLine orderLine2 = new OrderLine();
//        orderLine2.setProduct(product2);
//        orderLine2.setAmount(10);
//        orderLine2.setPurchasePrice((product2.getPrice() * orderLine2.getAmount()));
//
////Test PhoneNumbers
//
//        PhoneNumber phoneNumber = new PhoneNumber();
//        phoneNumber.setPhoneNumber("3800660001501");
//
//        PhoneNumber phoneNumber1 = new PhoneNumber();
//        phoneNumber.setPhoneNumber("3800660001502");
//
//        PhoneNumber phoneNumber2 = new PhoneNumber();
//        phoneNumber.setPhoneNumber("3800660001503");
//
//        //Test Clients
//        Client client = new Client();
//        client.setPhoneNumber(phoneNumber);
//        client.setName("Vasilii");
//        client.setSurname("Rukozhopov");
//
//        Client client1 = new Client();
//        client1.setPhoneNumber(phoneNumber1);
//        client1.setName("Andrei");
//        client1.setSurname("Konuh");
//
//        Client client2 = new Client();
//        client2.setPhoneNumber(phoneNumber2);
//        client2.setName("Stepan");
//        client2.setSurname("Andromedov");
//        //Test RoleSets
//        Set<Role> roleSet = new HashSet<Role>();
//        roleSet.add(Role.USER);
//
//        Set<Role> roleSet1 = new HashSet<Role>();
//        roleSet1.add(Role.USER);
//        roleSet1.add(Role.ADMIN);
//
//        Set<Role> roleSet2 = new HashSet<Role>();
//        roleSet2.add(Role.USER);
//
//        //Test Users
//
//        User user = new User();
//        user.setUsername("dima228");
//        user.setActive(true);
//        user.setPassword("dima228");
//        user.setRoles(roleSet);
//
//        User user1 = new User();
//        user1.setUsername("artem228");
//        user1.setActive(true);
//        user1.setPassword("artem228");
//        user1.setRoles(roleSet1);
//
//        User user2 = new User();
//        user2.setUsername("vasya228");
//        user2.setActive(true);
//        user2.setPassword("vasya228");
//        user2.setRoles(roleSet2);
//
//        ///Test List of OrderLines
//        List<OrderLine> orderLineList = new ArrayList<OrderLine>();
//        orderLineList.add(orderLine);
//        orderLineList.add(orderLine2);
//
//        List<OrderLine> orderLineList1 = new ArrayList<OrderLine>();
//        orderLineList1.add(orderLine1);
//        orderLineList1.add(orderLine2);
//
//        List<OrderLine> orderLineList2 = new ArrayList<OrderLine>();
//        orderLineList2.add(orderLine1);
//        orderLineList2.add(orderLine);
//
//
////Test Orders
//
//        Order order = new Order();
//        order.setClient(client);
//        order.setDeliveryPlace("Kiiv 2");
//        order.setDeliveryDate(new Date());
//        order.setManager(user);
//        order.setOrdersDate(new Date());
//        order.setOrderComment("Eto pevii zakaz");
//        order.setOrderLines(orderLineList);
//        order.setOrderVolumeGeneral(2.4);
//        order.setOrderWeight(5);
//        order.setPaymentMethod(PaymentMethod.BANK_TRANSFER);
//
//        Order order1 = new Order();
//        order1.setClient(client1);
//        order1.setDeliveryPlace("Odesa 39");
//        order1.setDeliveryDate(new Date());
//        order1.setManager(user1);
//        order1.setOrdersDate(new Date());
//        order1.setOrderComment("Eto vtoroi zakaz");
//        order1.setOrderLines(orderLineList1);
//        order1.setOrderVolumeGeneral(10.3);
//        order1.setOrderWeight(1);
//        order1.setPaymentMethod(PaymentMethod.COD);
//
//        Order order2 = new Order();
//        order2.setClient(client2);
//        order2.setDeliveryPlace("Kharkov 84");
//        order2.setDeliveryDate(new Date());
//        order2.setManager(user2);
//        order2.setOrdersDate(new Date());
//        order2.setOrderComment("Eto trerii zakaz");
//        order2.setOrderLines(orderLineList2);
//        order2.setOrderVolumeGeneral(111.1);
//        order2.setOrderWeight(2);
//        order2.setPaymentMethod(PaymentMethod.PICKUP);
//try {
//    orderService.save(order);
//    orderService.save(order1);
//    orderService.save(order2);
//}catch (NullPointerException e){
//    e.printStackTrace();
//}
    }
    @Bean
    public CommandLineRunner testDataProd(ProductService productService) {
        return (args) -> {
            // save a couple of customers

            Product product = new Product();
            product.setPrice(25.67);
            product.setDescription("Pnevmaticheskaya Vintowka");
            product.setName("Hatsan 125 TH");
            product.setQuantity(67);
            product.setWholesalePrice(21.01);


            Product product1 = new Product();
            product1.setPrice(100.26);
            product1.setDescription("CO2 Pistolet");
            product1.setName("KM44");
            product1.setQuantity(12);
            product1.setWholesalePrice(75.89);


            Product product2 = new Product();
            product2.setPrice(57.33);
            product2.setDescription("CO2 Pistolet");
            product2.setName("Osevoy Vint Hatsan Striker Edge");
            product2.setQuantity(53);
            product2.setWholesalePrice(7.67);


            productService.save(product);
            productService.save(product1);
            productService.save(product2);

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Product cProd : productService.findAll()) {
                log.info(cProd.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            productService.findById(1L)
                    .ifPresent(cProd -> {
                        log.info("Customer found with findById(1L):");
                        log.info("--------------------------------");
                        log.info(cProd.toString());
                        log.info("");
                    });

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            productService.findByProductName("Hatsan 125 TH").ifPresent(prodBN -> {
                log.info(prodBN.toString());
            });
            // for (Customer bauer : repository.findByLastName("Bauer")) {
            // 	log.info(bauer.toString());
            // }
            log.info("");
        };
    }

}

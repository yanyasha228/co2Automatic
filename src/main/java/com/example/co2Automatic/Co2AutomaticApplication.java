package com.example.co2Automatic;

import com.example.co2Automatic.models.*;
import com.example.co2Automatic.services.OrderService;
import com.example.co2Automatic.services.PhoneNumberService;
import com.example.co2Automatic.services.ProductCategoryService;
import com.example.co2Automatic.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Co2AutomaticApplication {
    private static final Logger log = LoggerFactory.getLogger(Co2AutomaticApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(Co2AutomaticApplication.class, args);
    }

//    @Bean("CRunner")
//    public CommandLineRunner testDataProd(ProductService productService ,
//                                          OrderService orderService,
//                                          PhoneNumberService phoneNumberService,
//                                          ProductCategoryService productCategoryService) {
//        return (args) -> {
//            // save a couple of customers
//
//            List<ProductCategory> productCategoryList = productCategoryService.findAll();
//            List<Product> productList = productService.findAll();
//
//            Client testClient = new Client();
//            testClient.setName();
//
//
//            PhoneNumber testPhoneNumber = new PhoneNumber();
//            testPhoneNumber.setPhoneNumber("0660001507");
//
//            Order testOrder = new Order();
//
//
//
//
//            // fetch all customers
//            log.info("Customers found with findAll():");
//            log.info("-------------------------------");
//            for (Product cProd : productService.findAll()) {
//                log.info(cProd.toString());
//            }
//            log.info("");
//
//            // fetch an individual customer by ID
//            productService.findById(1L)
//                    .ifPresent(cProd -> {
//                        log.info("Customer found with findById(1L):");
//                        log.info("--------------------------------");
//                        log.info(cProd.toString());
//                        log.info("");
//                    });
//
//            // fetch customers by last name
//            log.info("Customer found with findByLastName('Bauer'):");
//            log.info("--------------------------------------------");
//            productService.findByProductName("Hatsan 125 TH").ifPresent(prodBN -> {
//                log.info(prodBN.toString());
//            });
//            // for (Customer bauer : repository.findByLastName("Bauer")) {
//            // 	log.info(bauer.toString());
//            // }
//            log.info("");
//        };
//    }

}

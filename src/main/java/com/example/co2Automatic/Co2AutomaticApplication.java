package com.example.co2Automatic;

import com.example.co2Automatic.SystemComponents.AdminSettings;
import com.example.co2Automatic.models.*;
import com.example.co2Automatic.models.SessionModels.AdminModelSettings;
import com.example.co2Automatic.services.AdminModelSettingsService;
import com.example.co2Automatic.services.AdminModelSettingsServiceImpl;
import com.example.co2Automatic.services.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Co2AutomaticApplication {
    private static final Logger log = LoggerFactory.getLogger(Co2AutomaticApplication.class);


    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(Co2AutomaticApplication.class, args);

    }

    @Bean("CRunner")
    public CommandLineRunner testDataProd(ProductService productService) {
        return (args) -> {
            // save a couple of customers
            List<Product> productList = new ArrayList<>();


            Product product = new Product();
            product.setPrice(25.67);
            product.setDescription("Pnevmaticheskaya Vintowka");
            product.setImageUrl("http://co2.biz.ua/wp-content/uploads/2015/12/1664.jpg");
            product.setName("Hatsan 125 TH");
            product.setQuantity(67);
            product.setWholesalePrice(21.01);
            product.setProductStock(ProductStock.CO2_STOCK);
            productList.add(product);

            Product product1 = new Product();
            product1.setPrice(100.26);
            product1.setDescription("CO2 Pistolet");
            product1.setImageUrl("http://co2.biz.ua/wp-content/uploads/2015/12/1813.jpg");
            product1.setName("KM44");
            product1.setQuantity(12);
            product1.setWholesalePrice(75.89);
            product1.setProductStock(ProductStock.B_STOCK);
            productList.add(product1);

            Product product2 = new Product();
            product2.setPrice(57.33);
            product2.setDescription("CO2 Pistolet");
            product2.setName("Osevoy Vint Hatsan");
            product2.setImageUrl("http://co2.biz.ua/wp-content/uploads/2015/10/828.jpg");
            product2.setQuantity(53);
            product2.setWholesalePrice(7.67);
            product2.setProductStock(ProductStock.B_STOCK);
            productList.add(product2);

            Product product3 = new Product();
            product3.setPrice(200.33);
            product3.setDescription("CO2 Pistolet");
            product3.setName("Osevoy Vint Hatsan");
            product3.setImageUrl("http://co2.biz.ua/wp-content/uploads/2015/10/828.jpg");
            product3.setQuantity(533);
            product3.setWholesalePrice(56.78);
            product3.setProductStock(ProductStock.CO2_STOCK);
            productList.add(product3);

            Product product4 = new Product();
            product4.setPrice(87.56);
            product4.setDescription("CO2 Pistolet");
            product4.setName("Osevoy Vint Hatsan");
            product4.setImageUrl("http://co2.biz.ua/wp-content/uploads/2015/10/828.jpg");
            product4.setQuantity(76);
            product4.setWholesalePrice(7.67);
            product4.setProductStock(ProductStock.B_STOCK);
            productList.add(product4);

            Product product5 = new Product();
            product5.setPrice(190);
            product5.setDescription("CO2 Pistolet");
            product5.setName("Osevoy Vint Hatsan");
            product5.setImageUrl("http://co2.biz.ua/wp-content/uploads/2015/10/828.jpg");
            product5.setQuantity(53);
            product5.setWholesalePrice(7.67);
            product5.setProductStock(ProductStock.CO2_STOCK);
            productList.add(product5);

            Product product6 = new Product();
            product6.setPrice(57.33);
            product6.setDescription("CO2 Pistolet");
            product6.setName("Osevoy Vint Hatsan");
            product6.setImageUrl("http://co2.biz.ua/wp-content/uploads/2015/10/828.jpg");
            product6.setQuantity(53);
            product6.setWholesalePrice(7.67);
            product6.setProductStock(ProductStock.B_STOCK);
            productList.add(product6);

            Product product7 = new Product();
            product7.setPrice(57.33);
            product7.setDescription("CO2 Pistolet");
            product7.setName("Osevoy Vint Hatsan");
            product7.setImageUrl("http://co2.biz.ua/wp-content/uploads/2015/10/828.jpg");
            product7.setQuantity(53);
            product7.setWholesalePrice(7.67);
            product7.setProductStock(ProductStock.CO2_STOCK);
            productList.add(product7);

            Product product8 = new Product();
            product8.setPrice(57.33);
            product8.setDescription("CO2 Pistolet");
            product8.setName("Osevoy Vint Hatsan");
            product8.setImageUrl("http://co2.biz.ua/wp-content/uploads/2015/10/828.jpg");
            product8.setQuantity(53);
            product8.setWholesalePrice(7.67);
            product8.setProductStock(ProductStock.B_STOCK);
            productList.add(product8);

            Product product9 = new Product();
            product9.setPrice(57.33);
            product9.setDescription("CO2 Pistolet");
            product9.setName("Osevoy Vint Hatsan");
            product9.setImageUrl("http://co2.biz.ua/wp-content/uploads/2015/10/828.jpg");
            product9.setQuantity(53);
            product9.setWholesalePrice(7.67);
            product9.setProductStock(ProductStock.CO2_STOCK);
            productList.add(product9);

            Product product10 = new Product();
            product10.setPrice(57.33);
            product10.setDescription("CO2 Pistolet");
            product10.setName("Osevoy Vint Hatsan");
            product10.setImageUrl("http://co2.biz.ua/wp-content/uploads/2015/10/828.jpg");
            product10.setQuantity(53);
            product10.setWholesalePrice(7.67);
            product10.setProductStock(ProductStock.B_STOCK);
            productList.add(product10);

            Product product11 = new Product();
            product11.setPrice(5.33);
            product11.setDescription("CO2 Pistolet");
            product11.setName("Osevoy Vint Hatsan");
            product11.setImageUrl("http://co2.biz.ua/wp-content/uploads/2015/10/828.jpg");
            product11.setQuantity(53);
            product11.setWholesalePrice(7.67);
            product11.setProductStock(ProductStock.CO2_STOCK);
            productList.add(product11);

            Product product12 = new Product();
            product12.setPrice(57.33);
            product12.setDescription("CO2 Pistolet");
            product12.setName("Osevoy Vint Hatsan");
            product12.setImageUrl("http://co2.biz.ua/wp-content/uploads/2015/10/828.jpg");
            product12.setQuantity(53);
            product12.setWholesalePrice(7.67);
            product12.setProductStock(ProductStock.B_STOCK);
            productList.add(product12);

            for (Product prodForPersit : productList) productService.save(prodForPersit);

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

package com.example.co2Automatic.controllers;

import com.example.co2Automatic.dao.OrderDao;
import com.example.co2Automatic.models.Client;
import com.example.co2Automatic.models.Order;
import com.example.co2Automatic.models.PhoneNumber;
import com.example.co2Automatic.models.User;
import com.example.co2Automatic.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/editor")
public class EditorController {

    @Autowired
    OrderService orderService;

    @RequestMapping
    public String editorPage(Model model) {
        return "editor";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String submitOrder(@RequestParam String inputPhoneNumber,
                                @RequestParam Date inputDeliveryDate,
                                @RequestParam String inputPaymentMethod,
                                @RequestParam String inputName,
                                @RequestParam String inputSurname,
                                @RequestParam String inputCity,
                                @RequestParam int inputWarehouseNumber,
                                @RequestParam String inputOrderComment,
                                @RequestParam String[] field,
                                @RequestParam int[] qua) {
        Client newClient = new Client();
        newClient.setPhoneNumber(new PhoneNumber(inputPhoneNumber));
        newClient.setName(inputName);
        newClient.setSurname(inputSurname);
        Order newOrder = new Order();
        newOrder.setClient(newClient);
        newOrder.setOrdersDate(inputDeliveryDate);
        newOrder.setDeliveryDate(inputDeliveryDate);
        newOrder.setOrderComment(inputOrderComment);
        newOrder.setDeliveryPlace(inputCity);
        newOrder.setManager(new User());

        orderService.save(newOrder);


        return "redirect:../";
    }
}

package com.example.co2Automatic.controllers;

import com.example.co2Automatic.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/editOrder")
public class EditorController {

    @Autowired
    OrderService orderService;

    @RequestMapping
    public String editorPage(Model model) {

        model.addAttribute("nowDate", new Date());

        return "editOrder";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String submitOrder(@RequestParam String inputPhoneNumber,
                              @RequestParam String inputDeliveryDate,
                              @RequestParam String inputPaymentMethod,
                              @RequestParam String inputName,
                              @RequestParam String inputSurname,
                              @RequestParam String inputCity,
                              @RequestParam int inputWarehouseNumber,
                              @RequestParam String inputOrderComment,
                              @RequestParam String[] field,
                              @RequestParam int[] qua,
                              @RequestParam Double inputWeight,
                              @RequestParam Double inputVolume) {

orderService.addOrder(inputPhoneNumber,
        inputDeliveryDate,
        inputPaymentMethod,
        inputName,
        inputSurname,
        inputCity,
        inputWarehouseNumber,
        inputOrderComment,
        field,
        qua,
        inputWeight,
        inputVolume);

        return "redirect:../";
    }
}

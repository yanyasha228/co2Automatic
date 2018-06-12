package com.example.co2Automatic.controllers;

import com.example.co2Automatic.models.Order;
import com.example.co2Automatic.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private OrderService service;

    @RequestMapping
    public String mainPage(Model model) {
        model.addAttribute("orders", service.getAllOrders());
        return "main";
    }

    @RequestMapping(value = "/login")
    public String loginPage() {
        return "login";
    }
}

package com.example.co2Automatic.Controllers;

import com.example.co2Automatic.models.Order;
import com.example.co2Automatic.services.OrderService;
import com.example.co2Automatic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {


    @Autowired
    private UserService userService;

    @RequestMapping
    public String mainPage(Model model) {

        return "redirect:/orders";
    }


    @RequestMapping(value = "/login")
    public String loginPage() {
        return "login";
    }


}

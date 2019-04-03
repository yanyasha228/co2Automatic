package com.example.co2Automatic.Controllers;

import com.example.co2Automatic.models.Order;
import com.example.co2Automatic.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("orders")
public class OrdersController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public String orders(Model model){
        List<Order> orderList = orderService.getAllOrders();
        model.addAttribute("orders", orderList);

        return "orders";
    }
    @GetMapping("/create")
    public String createOrder(Model model){
        return "editOrCreateOrder";
    }


}

package com.example.co2Automatic.Controllers;

import com.example.co2Automatic.HelpUtils.CustomExceptions.ImpossibleEntityUpdatingException;
import com.example.co2Automatic.models.Order;
import com.example.co2Automatic.services.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("orders")
public class OrdersController {


    private static final Logger logger = Logger.getLogger(OrdersEditController.class);

    @Autowired
    OrderService orderService;

    @GetMapping
    public String orders(Model model) {
        List<Order> orderList = orderService.findAll();
        model.addAttribute("orders", orderList);

        return "orders";
    }

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    @ExceptionHandler({ImpossibleEntityUpdatingException.class})
    public String handleOrderManipulationException(Model model, Exception ex) {
        logger.error(ex.toString());

        model.addAttribute("error", ex.getClass().getName());

        return "error";
    }
}




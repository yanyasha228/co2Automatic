package com.example.co2Automatic.Controllers;


import com.example.co2Automatic.HelpUtils.CustomExceptions.ImpossibleSettingException;
import com.example.co2Automatic.HelpUtils.CustomExceptions.InsufficientAmountException;
import com.example.co2Automatic.models.Client;
import com.example.co2Automatic.models.Order;
import com.example.co2Automatic.models.User;
import com.example.co2Automatic.services.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("orders/create")
public class OrdersCreateController {

    @Autowired
    private OrderService orderService;

    private static final Logger logger = Logger.getLogger(OrdersEditController.class);

    @GetMapping
    public String createOrder(Model model) {

        model.addAttribute("order", new Order());
        model.addAttribute("client" , new Client());


        return "createOrder";
    }

    @GetMapping("submit")
    public String createOrderSubmit(Model model,
                                    @ModelAttribute Order order ,
                                    @ModelAttribute Client client ,
                                    @AuthenticationPrincipal User user ,
                                    @RequestParam() String[] prodOrderLineIdInput ,
                                    @RequestParam() String[] inputOrderLineProductQua) throws ImpossibleSettingException, InsufficientAmountException {

        int i = 0;
        orderService.save(order);

        return "orders";

    }

    @ExceptionHandler({ImpossibleSettingException.class,
            InsufficientAmountException.class})
    public String handleOrderManipulationException(Model model, Exception ex) {
        logger.error(ex.toString());

        model.addAttribute("error", ex.getClass().getName());

        return "error";
    }

}

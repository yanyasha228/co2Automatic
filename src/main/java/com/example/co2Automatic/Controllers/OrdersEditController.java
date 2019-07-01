package com.example.co2Automatic.Controllers;

import com.example.co2Automatic.HelpUtils.CustomExceptions.ImpossibleEntityUpdatingException;
import com.example.co2Automatic.HelpUtils.CustomExceptions.ImpossibleSettingException;
import com.example.co2Automatic.HelpUtils.CustomExceptions.InsufficientAmountException;
import com.example.co2Automatic.HelpUtils.CustomExceptions.OrderNotFoundException;
import com.example.co2Automatic.models.Order;
import com.example.co2Automatic.services.ClientService;
import com.example.co2Automatic.services.OrderService;
import com.example.co2Automatic.services.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("orders/{id}/edit")
public class OrdersEditController {

    private static final Logger logger = Logger.getLogger(OrdersEditController.class);

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    ClientService clientService;

    @RequestMapping
    public String editorPage(Model model, @PathVariable Long id) throws OrderNotFoundException {


            Order order = orderService.findById(id).orElseThrow(() -> new OrderNotFoundException("No order found with id" + id));
            model.addAttribute("order", order);
            model.addAttribute("client", order.getClient());


        return "editOrder";
    }

    ////Dont forget change request method type
    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String submitOrder(Model model,
                              @ModelAttribute Order order) throws ImpossibleSettingException, InsufficientAmountException, ImpossibleEntityUpdatingException {


        orderService.update(order);

        return "redirect:../";
    }


    @ExceptionHandler({ImpossibleSettingException.class,
            InsufficientAmountException.class,
            ImpossibleEntityUpdatingException.class})
    public String handleOrderManipulationException(Model model, Exception ex) {
        logger.error(ex.toString());

        model.addAttribute("error", ex.getClass().getName());

        return "error";
    }


}

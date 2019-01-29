package com.example.co2Automatic.Controllers;

import com.example.co2Automatic.CustomExceptions.ImpossibleSettingException;
import com.example.co2Automatic.CustomExceptions.InsufficientAmountException;
import com.example.co2Automatic.models.Client;
import com.example.co2Automatic.models.Order;
import com.example.co2Automatic.models.PaymentMethod;
import com.example.co2Automatic.models.Product;
import com.example.co2Automatic.services.ClientService;
import com.example.co2Automatic.services.OrderService;
import com.example.co2Automatic.services.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/editOrder")
public class OrderEditorController {

    private static final Logger logger = Logger.getLogger(OrderEditorController.class);

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    ClientService clientService;

    @RequestMapping
    public String editorPage(Model model, @RequestParam(required = false) Long orderId) {

        if (orderId != null) {
            Order order = orderService.findById(orderId).orElse(new Order());
            model.addAttribute("order", order);
            model.addAttribute("client", order.getClient());
        } else model.addAttribute("order", new Order());
        Order ord = new Order();
        ord.setDeliveryDate(new Date());
        model.addAttribute("order", ord);
        model.addAttribute("client", new Client());

        return "editOrder";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String submitOrder(@RequestParam(required = false) Long orderId,
                              @RequestParam(required = false) Long clientId,
                              @RequestParam(required = false) String inputEmail,
                              @RequestParam(required = false) String inputPhoneNumber,
                              @RequestParam(required = false) String inputDeliveryDate,
                              @RequestParam(required = false) PaymentMethod inputPaymentMethod,
                              @RequestParam(required = false) String inputName,
                              @RequestParam(required = false) String inputLastName,
                              @RequestParam(required = false) String inputMiddleName,
                              @RequestParam(required = false) String inputCity,
                              @RequestParam(required = false) Integer inputWarehouseNumber,
                              @RequestParam(required = false) String inputOrderComment,
                              @RequestParam(required = false) Integer[] prodOrderLineIdInput,
                              @RequestParam(required = false) Integer[] inputOrderLineProductQua,
                              @RequestParam(required = false) Double inputWeight,
                              @RequestParam(required = false) Double inputVolume) throws ImpossibleSettingException, InsufficientAmountException {


        orderService.updateOrder(orderId,
                clientId,
                inputEmail,
                inputPhoneNumber,
                inputDeliveryDate,
                inputPaymentMethod,
                inputName,
                inputLastName,
                inputMiddleName,
                inputCity,
                inputWarehouseNumber,
                inputOrderComment,
                prodOrderLineIdInput,
                inputOrderLineProductQua);

        return "redirect:../";
    }


    @ExceptionHandler({ImpossibleSettingException.class, InsufficientAmountException.class})
    public String handleOrderManipulationException(Model model ,Exception ex) {
        logger.error(ex.toString());

        model.addAttribute("error" , ex.getClass().getName());

        return "error";
    }


}

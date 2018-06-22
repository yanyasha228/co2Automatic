package com.example.co2Automatic.controllers;

import com.example.co2Automatic.dao.OrderDao;
import com.example.co2Automatic.models.Client;
import com.example.co2Automatic.models.Order;
import com.example.co2Automatic.models.PhoneNumber;
import com.example.co2Automatic.models.User;
import com.example.co2Automatic.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("/editor")
public class EditorController {

    @Autowired
    OrderService orderService;

    @RequestMapping
    public String editorPage(Model model) {

        model.addAttribute("nowDate", new Date());

        return "editor";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String submitOrder(@AuthenticationPrincipal User user,
                              @RequestParam long inputPhoneNumber,
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


        return "redirect:../";
    }
}

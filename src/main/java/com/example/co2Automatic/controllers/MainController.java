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

    ///////
    @RequestMapping(value = "/editor")
    public String editorPage(Model model) {
        model.addAttribute("order", new Order());
        return "editor";
    }

    @RequestMapping(value = "/editor/submit", method = RequestMethod.GET)
    public String submitArticle(@RequestParam() String[] field,@RequestParam() int[] qua, @RequestParam("comment") String comment) {
        String[] fields = field;
        int[] fqua = qua;
        String com = comment;
        return "redirect:../";
    }

    @RequestMapping(value = "/login")
    public String loginPage() {
        return "login";
    }
}

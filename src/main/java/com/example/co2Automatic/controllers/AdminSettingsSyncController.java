package com.example.co2Automatic.controllers;

import com.example.co2Automatic.DataManipulationHelpers.ProductsTableValidator;
import com.example.co2Automatic.DataManipulationHelpers.ProductsXmlUnmarshaller;
import com.example.co2Automatic.SystemComponents.AdminSettings;
import com.example.co2Automatic.services.AdminModelSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("admin/settings/sync")
public class AdminSettingsSyncController {


    @Autowired
    ProductsTableValidator productsTableValidator;

    @Autowired
    private AdminSettings adminSettings;

    @RequestMapping
    public String adminSettingsSync(Model model){
        return "adminSettingsSync";
    }

    @PostMapping("submit")
    public String validateProductsTable(Model model , @RequestParam String syncUrl){
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        HttpEntity<String> entity = new HttpEntity<String>(new HttpHeaders());

        ResponseEntity<String> response = restTemplate.exchange(syncUrl, //
                HttpMethod.GET, entity, String.class);

        HttpHeaders headers = response.getHeaders();

//        if (headers.get("Content-Type").contains("text/xml") && response.getStatusCode().is2xxSuccessful()){
//            ProductsXmlUnmarshaller productsXmlUnmarshaller = new ProductsXmlUnmarshaller(response.getBody()).buildXml();
//            productsTableValidator.validateCategoriesTable(productsXmlUnmarshaller.getCategoriesFromXml());
//            productsTableValidator.validateProductsTable(productsXmlUnmarshaller.getProductListFromXml());
//        }

        return "redirect:../";
    }
}

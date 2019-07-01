package com.example.co2Automatic.Controllers;

import com.example.co2Automatic.HelpUtils.DataManipulationHelpers.ProductsTableValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBException;
import java.nio.charset.Charset;

@Controller
@RequestMapping("settings/sync")
public class SettingsSyncController {

    @Autowired
    ProductsTableValidator productsTableValidator;

    @RequestMapping
    public String adminSettingsSync(Model model) {
        return "settingsSync";
    }

    @PostMapping("submit")
    public String validateProductsTable(Model model, @RequestParam String syncUrl) throws JAXBException {

        syncProductsTable(syncUrl);

        return "redirect:../";
    }

    private void syncProductsTable(final String syncUrl){
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        HttpEntity<String> entity = new HttpEntity<String>(new HttpHeaders());

        ResponseEntity<String> response = restTemplate.exchange(syncUrl, //
                HttpMethod.GET, entity, String.class);

        HttpHeaders headers = response.getHeaders();

        if (headers.get("Content-Type").contains("text/xml") && response.getStatusCode().is2xxSuccessful()) {

            productsTableValidator.validateProductsAndProductCategoriesTableByXmlString(response
                    .getBody().replaceAll("<!DOCTYPE yml_catalog SYSTEM \"shops.dtd\">", ""));

        }
    }

}

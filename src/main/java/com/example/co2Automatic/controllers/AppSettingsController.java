package com.example.co2Automatic.controllers;


import com.example.co2Automatic.models.SessionModels.ApplicationSettingsDataModel;
import com.example.co2Automatic.services.ApplicationSettingsDataModelService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/AppSettings")
public class AppSettingsController {

    @Autowired
    ApplicationSettingsDataModelService applicationSettingsDataModelService;

    @RequestMapping(value = "/getAppSettings" , method = RequestMethod.GET)
    public ApplicationSettingsDataModel getAppSettingsData(){

        ApplicationSettingsDataModel AS = applicationSettingsDataModelService.getSettings();
        return AS;

    }
}

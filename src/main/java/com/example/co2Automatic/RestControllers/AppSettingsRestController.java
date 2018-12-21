package com.example.co2Automatic.RestControllers;


import com.example.co2Automatic.models.SessionModels.ApplicationSettingsDataModel;
import com.example.co2Automatic.services.ApplicationSettingsDataModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restApi/AppSettings")
public class AppSettingsRestController {

    @Autowired
    ApplicationSettingsDataModelService applicationSettingsDataModelService;

    @RequestMapping(value = "/getAppSettings", method = RequestMethod.GET)
    public ApplicationSettingsDataModel getAppSettingsData() {

        applicationSettingsDataModelService.getSettings();
        return applicationSettingsDataModelService.getSettings();

    }

}

package com.example.co2Automatic.RestControllers;


import com.example.co2Automatic.models.SessionModels.AppSettingsModel;
import com.example.co2Automatic.services.AppSettingsModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restApi/AppSettings")
public class AppSettingsRestController {

    @Autowired
    private AppSettingsModelService appSettingsModelService;

    @GetMapping
    public AppSettingsModel getAppSettingsData() {

        return appSettingsModelService.getSettings().orElse(null);

    }

}

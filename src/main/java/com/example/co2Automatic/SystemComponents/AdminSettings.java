package com.example.co2Automatic.SystemComponents;

import com.example.co2Automatic.models.SessionModels.AdminModelSettings;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;

import javax.persistence.Column;

@Data
public class AdminSettings {

    public AdminSettings() {
    }

    private double usd_currency;

    private double eur_currency;

    public void setSettings(AdminModelSettings adminModelSettings){
        this.usd_currency = adminModelSettings.getUsd_currency();
        this.eur_currency = adminModelSettings.getEur_currency();
    }

}

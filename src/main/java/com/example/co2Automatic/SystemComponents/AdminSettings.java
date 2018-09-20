package com.example.co2Automatic.SystemComponents;

import com.example.co2Automatic.models.SessionModels.AdminModelSettings;
import lombok.Data;

@Data
public class AdminSettings {

    public AdminSettings() {
    }

    private double usd_currency;

    private double eur_currency;

    public void setSettings(AdminModelSettings adminModelSettings) {
        this.usd_currency = adminModelSettings.getUsd_currency();
        this.eur_currency = adminModelSettings.getEur_currency();
    }

}

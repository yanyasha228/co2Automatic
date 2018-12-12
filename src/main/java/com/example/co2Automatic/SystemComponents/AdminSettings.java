package com.example.co2Automatic.SystemComponents;

import com.example.co2Automatic.models.SessionModels.AdminSettingsModel;
import lombok.Data;

@Data
public class AdminSettings {

    public AdminSettings() {
    }

    private double usd_currency;

    private double eur_currency;

    public void setSettings(AdminSettingsModel adminSettingsModel) {
        this.usd_currency = adminSettingsModel.getUsd_currency();
        this.eur_currency = adminSettingsModel.getEur_currency();
    }

}

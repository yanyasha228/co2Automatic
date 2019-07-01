package com.example.co2Automatic.SystemComponents;

import com.example.co2Automatic.models.SessionModels.AppSettingsModel;
import lombok.Data;

@Data
public class AdminSettings {

    public AdminSettings() {
    }

    private double usd_currency;

    private double eur_currency;

    public void setSettings(AppSettingsModel appSettingsModel) {
        this.usd_currency = appSettingsModel.getUsdCurrency();
        this.eur_currency = appSettingsModel.getEurCurrency();
    }

}

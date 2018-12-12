package com.example.co2Automatic.models.SessionModels;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ApplicationSettingsDataModel {

    public ApplicationSettingsDataModel() {
    }

    @Id
    private long id = 1L;

    @Column(name = "USD_CURRENCY")
    private double usd_currency;

    @Column(name = "EUR_CURRENCY")
    private double eur_currency;


}

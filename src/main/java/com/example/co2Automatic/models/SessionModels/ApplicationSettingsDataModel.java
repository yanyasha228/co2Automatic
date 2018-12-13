package com.example.co2Automatic.models.SessionModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "app_settings_data")
@JsonIgnoreProperties(value = "id")
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

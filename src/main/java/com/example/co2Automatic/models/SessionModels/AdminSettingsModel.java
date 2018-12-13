package com.example.co2Automatic.models.SessionModels;

import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Data
@Entity
@Table(name = "admin_settings")
public class AdminSettingsModel {

    @Id
    private long id = 1L;

    @Column(name = "USD_CURRENCY")
    private double usd_currency;

    @Column(name = "EUR_CURRENCY")
    private double eur_currency;


}

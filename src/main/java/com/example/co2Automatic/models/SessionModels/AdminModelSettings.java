package com.example.co2Automatic.models.SessionModels;

import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Data
@Entity
public class AdminModelSettings {

    public AdminModelSettings() {
        this.id = 1;
    }

    @Id
    private long id;

    @Column(name = "USD_CURRENCY")
    private double usd_currency;

    @Column(name = "EUR_CURRENCY")
    private double eur_currency;

}

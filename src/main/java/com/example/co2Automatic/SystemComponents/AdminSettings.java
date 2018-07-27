package com.example.co2Automatic.SystemComponents;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;

import javax.persistence.Column;

@Component("adminSettings")
@ApplicationScope
@Data
public class AdminSettings {

    public AdminSettings() {
    }

    private double usd_currency;

    private double eur_currency;

}

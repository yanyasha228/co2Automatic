package com.example.co2Automatic.models.SessionModels;

import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Data
@Entity
@Table(name = "admin_settings")
public class AppSettingsModel {

    @Id
    private Long id = 1L;

    @Column(name = "USD_CURRENCY")
    private Double usdCurrency;

    @Column(name = "EUR_CURRENCY")
    private Double eurCurrency;


}

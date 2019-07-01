package com.example.co2Automatic.models.HelpModels;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product_params")
public class ProductParam {

    public ProductParam() {
    }

    public ProductParam(String paramName, String paramValue) {
        this.paramName = paramName;
        this.paramValue = paramValue;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "param_name")
    private String paramName;

    @Column(name = "param_value")
    private String paramValue;

}

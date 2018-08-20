package com.example.co2Automatic.models.HelpModels;


import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "param")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductParam {

    @XmlAttribute(name = "name")
    private String paramName;

    @XmlElement(name = "param")
    private String paramValue;
}

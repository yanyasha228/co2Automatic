package com.example.co2Automatic.models.HelpModels;

import com.example.co2Automatic.models.Product;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "yml_catalog")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductList {

    @XmlElement(name = "offers")
    private List<Product> productsList;

}

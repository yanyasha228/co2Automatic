package com.example.co2Automatic.models;

import com.example.co2Automatic.DataManipulationHelpers.XmlAdapters.MoneyCurrencyXmlAdapter;
import com.example.co2Automatic.DataManipulationHelpers.XmlAdapters.ParametersXmlAdapter;
import com.example.co2Automatic.models.SessionModels.MoneyCurrency;
import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.*;

@Data
@Entity
@Table(name = "product")
@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {

    public Product() {
        creationDate = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @XmlElement(name = "name")
    @Column(name = "name")
    private String name;

    @XmlElement(name = "url")
    @Column(name = "product_url_from_external_resource")
    private String productUrlFromExternalResource;

    @Column(name = "quantity")
    private int quantity;

    @XmlElement(name = "currencyId")
    @XmlJavaTypeAdapter(MoneyCurrencyXmlAdapter.class)
    @Column(name = "moneyCurrency")
    @Enumerated(EnumType.STRING)
    private MoneyCurrency currency;


    @XmlElement(name = "description")
    @Column(name = "description")
    private String description;

    @XmlElement(name = "categoryId")
    @Transient
    private int categoryXmlId;

    @XmlElement(name = "param")
    @XmlJavaTypeAdapter(ParametersXmlAdapter.class)
    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "product_params")
    @MapKeyColumn(name = "product_param_name")
    @Column(name = "params")
    private Map<String, String> params = new HashMap<>();

    @XmlElement(name = "price")
    @Column(name = "price")
    private double price;

    @Column(name = "wholesale_price")
    private double wholesalePrice;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory productCategory;

    @XmlElement(name = "country_of_origin")
    @Column(name = "country_of_origin")
    private String countryOfOrigin;


    @Column(name = "product_stock")
    @Enumerated(EnumType.STRING)
    private ProductStock productStock;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "last_updating_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatingDate;

    @XmlElement(name = "picture")
    @ElementCollection
    @CollectionTable(name = "urls")
    @Column(name = "image_urls")
    private List<String> imageUrls = new ArrayList<String>();

    @XmlElement(name = "vendor")
    @Column(name = "vendor")
    private String vendor;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderLine> orderLines = new ArrayList<OrderLine>();

    @Column(name = "available")
    private boolean available;

    //    @ManyToMany(mappedBy = "products")
//    private List<Order> ordersList;
    @PreUpdate
    @PrePersist
    protected void modifyLastUpdatingDate() {
        if (quantity > 0) {
            available = true;
        } else available = false;
        lastUpdatingDate = new Date();
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", addingDate=" + creationDate +
                ", imageUrls='" + imageUrls + '\'' +
                '}';
    }
}

package com.example.co2Automatic.models;

import com.example.co2Automatic.models.SessionModels.MoneyCurrency;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "product")
public class Product {

    public Product(){
        creationDate = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "product_url_from_external_resource")
    private String productUrlFromExternalResource;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "moneyCurrency")
    private MoneyCurrency currency;

    @Column(name = "description")
    private String description;

    @Transient
    private int categorryXmlId;

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "product_params")
    @MapKeyColumn(name="product_param_name")
    @Column(name = "params")
    private Map<String,String> params;

    @Column(name = "price")
    private double price;

    @Column(name = "wholesale_price")
    private double wholesalePrice;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory productCategory;

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

    @ElementCollection
    @CollectionTable(name="urls")
    @Column(name = "image_urls")
    private List<String> imageUrls = new ArrayList<String>();

    @Column(name = "vendor")
    private String vendor;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderLine> orderLines = new ArrayList<OrderLine>();

    //    @ManyToMany(mappedBy = "products")
//    private List<Order> ordersList;
 @PreUpdate
 @PrePersist
 protected void modifyLastUpdatingDate()
 {
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

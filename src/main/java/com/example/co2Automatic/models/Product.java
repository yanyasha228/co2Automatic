package com.example.co2Automatic.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "product")
@JsonIgnoreProperties(value = { "currency",
        "productUrlFromExternalResource",
        "description",
        "params",
        "wholeSalePrice",
        "productCategory",
        "countryOfOrigin",
        "productStock",
        "creationDate",
        "lastUpdatingDate",
        "vendor",
        "orderLines"
})
public class Product {

    public Product() {
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
    @Enumerated(EnumType.STRING)
    private MoneyCurrency currency;

    //    @Lob
//    @Basic(fetch = FetchType.LAZY)
    @Column(name = "description" , length = 20000)
    private String description;

    @Transient
    private int categoryXmlId;

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "product_params")
    @MapKeyColumn(name = "product_param_name")
    @Column(name = "params")
    private Map<String, String> params = new HashMap<>();


    @Column(name = "price")
    private double price;

    @Column(name = "wholesale_price")
    private double wholeSalePrice;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory productCategory;


    @Column(name = "country_of_origin")
    private String countryOfOrigin;


    @Column(name = "product_stock")
    @Enumerated(EnumType.STRING)
    private ProductStock productStock = ProductStock.NO_STOCK;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "last_updating_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatingDate;

    @ElementCollection
    @CollectionTable(name = "urls")
    @Column(name = "image_urls")
    private Set<String> imageUrls = new HashSet<>();


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

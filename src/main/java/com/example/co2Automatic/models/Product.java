package com.example.co2Automatic.models;

import lombok.Data;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "wholesale_price")
    private double wholesalePrice;

    @Column(name = "product_category")
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderLine> orderLines = new ArrayList<OrderLine>();

    //    @ManyToMany(mappedBy = "products")
//    private List<Order> ordersList;

    @PrePersist
    protected void onCreate() {
        creationDate = new Date();
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", addingDate=" + creationDate +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}

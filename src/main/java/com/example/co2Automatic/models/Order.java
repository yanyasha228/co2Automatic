package com.example.co2Automatic.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @Column(name = "order_comment")
    private String orderComment;

    @Column(name = "delivery_place")
    private String deliveryPlace;

    @Column(name = "delivery_place_warehouse_number")
    private int deliveryPlaceWarehouseNumber;

    @Column(name = "delivery_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "manager", referencedColumnName = "id")
    private User manager;

    @Column(name = "order_volume_general")
    private double OrderVolumeGeneral;

    @Column(name = "order_weight")
    private double OrderWeight;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

//    @ManyToMany
//    @JoinTable(
//            name = "products_in_order",
//            joinColumns = {@JoinColumn(name = "order_id")},
//            inverseJoinColumns = {@JoinColumn(name = "product_id")}
//    )
//    private List<Product> products;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<OrderLine> orderLines = new ArrayList<OrderLine>();

    @Column(name = "order_creation_ate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderCreationDate;

    @PrePersist
    protected void onCreate(){
        orderCreationDate = new Date();
    }
}

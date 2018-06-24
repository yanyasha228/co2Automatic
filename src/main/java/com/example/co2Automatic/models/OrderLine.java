package com.example.co2Automatic.models;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "order_line")
public class OrderLine {

    public OrderLine() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    @Column(name = "amount")
    private int amount;

    @Column(name = "purchase_price")
    private double purchasePrice;

}

package com.example.co2Automatic.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id",referencedColumnName="id",nullable=false)
    private Client client;

    @Column(name = "manager_Id")
    private int ManagerId;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @ManyToMany
    @JoinTable(
            name = "products_in_order",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private List<Product> products;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ordersDate")
    private Date ordersDate;

    public Order() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getManagerId() {
        return ManagerId;
    }

    public void setManagerId(int managerId) {
        ManagerId = managerId;
    }

    public List<Product> getProduct() {
        return products;
    }

    public void setProduct(List<Product> product) {
        this.products = product;
    }

    public Date getOrdersDate() {
        return ordersDate;
    }

    public void setOrdersDate(Date ordersDate) {
        this.ordersDate = ordersDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                ManagerId == order.ManagerId &&
                Objects.equals(client, order.client) &&
                Objects.equals(products, order.products) &&
                Objects.equals(ordersDate, order.ordersDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, client, ManagerId, products, ordersDate);
    }
}

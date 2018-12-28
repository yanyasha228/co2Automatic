package com.example.co2Automatic.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "order_line")
public class OrderLine {

    public OrderLine() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER , cascade = {CascadeType.PERSIST , CascadeType.REFRESH , CascadeType.MERGE})
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    public void deleteOrderLine(){
        product.increaseAmount(amount);
        product.deleteOrderLine(this);
        order = null;
        product = null;
    }

    public void createOrderLine(Product product , Order order){
        product.decreaseAmount(amount);
        product.addOrderLine(this);
    }

    @Column(name = "amount")
    private int amount;

    @Column(name = "purchase_price")
    private double purchasePrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLine orderLine = (OrderLine) o;
        return id == orderLine.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @PrePersist
    protected void addReservedAmountOfProducts(){
        product.decreaseAmount(amount);
        product.addOrderLine(this);
    }

    @PreRemove
    protected void removeReservedAmountOfProducts(){
        product.increaseAmount(amount);
        product.deleteOrderLine(this);
    }
}

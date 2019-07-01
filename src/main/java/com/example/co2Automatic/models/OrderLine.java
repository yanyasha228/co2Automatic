package com.example.co2Automatic.models;

import com.example.co2Automatic.HelpUtils.CustomExceptions.InsufficientAmountException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@Table(name = "order_lines")
public class OrderLine implements Serializable {

    public OrderLine() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER , cascade = {CascadeType.PERSIST , CascadeType.REFRESH , CascadeType.MERGE })
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    @NotNull
    @Setter(AccessLevel.NONE)
    private Product product;


    @Column(name = "amount")
    @Setter(AccessLevel.NONE)
    private int amount;

    @Column(name = "purchase_price" , nullable = false)
    private double purchasePrice;

    @Column(name = "discount")
    private double discount;

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

    public void setProductWithAmount(Product product , int amount) throws InsufficientAmountException {

        if(this.product!= null){
            this.product.removeOrderLine(this);
            this.product = null;
        }
        if(product.getQuantity()< amount){
            throw new InsufficientAmountException("Product:"+
                    product.getName()
                    + " With id:" +
                    product.getId()
                    + " has insufficient quantity");
        }else{
            this.amount = amount;
            this.product = product;
            product.addOrderLine(this);
        }

    }

    @PreRemove
    protected void removeReservedAmountOfProducts(){
        product.removeOrderLine(this);
        this.order.getOrderLines().remove(this);
    }
}

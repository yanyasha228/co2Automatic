package com.example.co2Automatic.models;

import com.example.co2Automatic.CustomExceptions.ImpossibleSettingException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Data
@Entity
@Table(name = "orders")
public class Order implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @Column(name = "order_comment")
    private String orderComment;

    @Column(name = "delivery_place")
    private String deliveryPlace;

    @Column(name = "delivery_place_warehouse_number")
    private Integer deliveryPlaceWarehouseNumber;

    @Column(name = "delivery_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "manager", referencedColumnName = "id")
    private User manager;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "order_volume_general")
    private double OrderVolumeGeneral;

    @Column(name = "order_summary_price")
    private double orderSummaryPrice;

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

////    private List<Product> products;    public void addOrderLine(OrderLine orderLine){
////        orderLines.add(orderLine);
////        orderLine.setOrder(this);
////    }
////
////    public void removeOrderLine(OrderLine orderLine){
////        orderLines.remove(orderLine);
////        orderLine.setOrder(null);
////    }
////
////    public void deleteAllOrderLines(){
////        for(OrderLine tOrdLine : orderLines){
////            removeOrderLine(tOrdLine);
////        }
////    }

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.PROTECTED)
    private Set<OrderLine> orderLines = new HashSet<>();

    public Set<OrderLine> getOrderLinesImmutable() {
        return Collections.unmodifiableSet(orderLines);
    }

    public void setOrderLines(Set<OrderLine> orderLines) throws ImpossibleSettingException {
        if (this.orderLines.isEmpty()) {
            orderLines.forEach(orderLine -> orderLine.setOrder(this));
            this.orderLines.addAll(orderLines);
        } else {
            throw new ImpossibleSettingException("Orderlines Set is't empty! " +
                    "You have to empty it using one of the removal methods provided by OrderLineService!");
        }
    }

    public void setClient(Client client) {
        if (client != null && !client.equals(this.client)) {
          this.client = client;
          client.getClientsOrders().add(this);
        }
    }
//

    @Column(name = "order_creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderCreationDate;

    @PrePersist
    protected void onPersist() {
        orderCreationDate = new Date();
    }

}

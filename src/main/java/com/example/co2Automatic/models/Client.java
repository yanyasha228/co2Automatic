package com.example.co2Automatic.models;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "usual_delivery_place")
    private String usualDeliveryPlace;

    @Column(name = "usual_warehouse_number")
    private int usualWarehouseNumber;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<PhoneNumber> phoneNumbers = new HashSet<PhoneNumber>();

//    @Column(name = "wholesaler")
//    private boolean wholesaler;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> clientsOrders = new ArrayList<Order>();


}

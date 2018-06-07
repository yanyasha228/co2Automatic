package com.example.co2Automatic.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private PhoneNumber phoneNumber;

    @Column(name = "wholesaler")
    private boolean wholesaler;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Order> clientsOrders;

    public Client() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getClientsOrders() {
        return clientsOrders;
    }

    public void setClientsOrders(List<Order> clientsOrders) {
        this.clientsOrders = clientsOrders;
    }

    public boolean isWholesaler() {
        return wholesaler;
    }

    public void setWholesaler(boolean wholesaler) {
        this.wholesaler = wholesaler;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

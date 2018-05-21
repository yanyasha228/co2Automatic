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


    @OneToMany( mappedBy = "client",fetch = FetchType.EAGER)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id &&
                Objects.equals(name, client.name) &&
                Objects.equals(clientsOrders, client.clientsOrders);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, clientsOrders);
    }
}

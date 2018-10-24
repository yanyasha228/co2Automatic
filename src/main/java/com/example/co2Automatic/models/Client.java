package com.example.co2Automatic.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "clients")
@JsonIgnoreProperties(value = { "clientsOrders"})
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

    @Column(name = "email")
    private String email;

    @Column(name = "usual_delivery_place")
    private String usualDeliveryPlace;

    @Column(name = "client_status")
    private ClientStatus clientStatus;

    @Column(name = "usual_warehouse_number")
    private int usualWarehouseNumber;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private PhoneNumber phoneNumber;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

//    @Column(name = "wholesaler")
//    private boolean wholesaler;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Order> clientsOrders = new ArrayList<Order>();

    public void setPhoneNumber(PhoneNumber newPhoneNumber) {
        //prevent endless loop
        if (sameAsFormer(newPhoneNumber))
            return;
        //set new facebook account
        PhoneNumber oldPhoneNumber = this.phoneNumber;
        this.phoneNumber = newPhoneNumber;
        //remove from the old facebook account
        if (oldPhoneNumber!=null)
            oldPhoneNumber.setClient(null);
        //set myself into new facebook account
        if (newPhoneNumber!=null)
            newPhoneNumber.setClient(this);
    }

    private boolean sameAsFormer(PhoneNumber newPhoneNumber) {
        return phoneNumber == null ?
                newPhoneNumber == null : phoneNumber.equals(newPhoneNumber);
    }

}

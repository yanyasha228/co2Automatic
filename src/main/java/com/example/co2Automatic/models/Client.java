package com.example.co2Automatic.models;

import com.example.co2Automatic.models.ModelEnums.ClientStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "clients")
@JsonIgnoreProperties(value = {"clientsOrders"})
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "middleName")
    private String middleName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "usual_delivery_place")
    private String usualDeliveryPlace;

    @Column(name = "client_status")
    private ClientStatus clientStatus;

    @Column(name = "usual_warehouse_number")
    private Integer usualWarehouseNumber;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

//    @Column(name = "wholesaler")
//    private boolean wholesaler;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "client" , cascade = CascadeType.ALL)
    private List<Order> clientsOrders = new ArrayList<Order>();

    @PrePersist
    protected void onCreate() {
        creationDate = LocalDate.now();
    }


//    public void setPhoneNumber(PhoneNumber newPhoneNumber) {
//        //prevent endless loop
//        if (sameAsFormer(newPhoneNumber))
//            return;
//        //set new facebook account
//        PhoneNumber oldPhoneNumber = this.phoneNumber;
//        this.phoneNumber = newPhoneNumber;
//        //remove from the old facebook account
//        if (oldPhoneNumber!=null)
//            oldPhoneNumber.setClient(null);
//        //set myself into new facebook account
//        if (newPhoneNumber!=null)
//            newPhoneNumber.setClient(this);
//    }
//
//    private boolean sameAsFormer(PhoneNumber newPhoneNumber) {
//        return phoneNumber == null ?
//                newPhoneNumber == null : phoneNumber.equals(newPhoneNumber);
//    }

}

package com.example.co2Automatic.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@Table(name = "phone_numbers")
public class PhoneNumber implements Serializable {

    public PhoneNumber() {


    }

    public PhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return this.phoneNumber.equalsIgnoreCase(that.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }
}

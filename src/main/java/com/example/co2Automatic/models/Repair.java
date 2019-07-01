package com.example.co2Automatic.models;

import lombok.Data;


import javax.persistence.*;

@Data
@Entity
@Table(name = "repairs")
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "repair_status")
    private String repairStatus;

    @Column(name = "problem_description")
    private String problemDescription;

    public Repair() {
    }
}

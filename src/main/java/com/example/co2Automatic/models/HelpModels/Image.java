package com.example.co2Automatic.models.HelpModels;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "images")
public class Image {

    public Image() {
    }

    public Image(String url) {
        this.url = url;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String url;

}

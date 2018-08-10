package com.example.co2Automatic.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class ProductCategory {

    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private ProductCategory parent;

    @OneToMany(mappedBy = "parent")
    private Set<ProductCategory> subCategories;

    @OneToMany(mappedBy = "productCategory")
    private List<Product> productsInCategory;

}

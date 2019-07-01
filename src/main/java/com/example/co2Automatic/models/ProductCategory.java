package com.example.co2Automatic.models;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "product_categories")
public class ProductCategory implements Serializable {

    public ProductCategory() {

    }

    public ProductCategory(Integer id, String name) {

        this.id = id;
        this.name = name;

    }


    @Id
    private Integer id;

    @Transient
    private Integer parentId;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private ProductCategory parent;

    @OneToMany(mappedBy = "parent")
    private Set<ProductCategory> subCategories;

    @OneToMany(mappedBy = "productCategory")
    private List<Product> productsInCategory;

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", parent=" + parent +
                ", subCategories=" + subCategories +
                '}';
    }
}

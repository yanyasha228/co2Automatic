package com.example.co2Automatic.models;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductCategory {

    @Id
    @XmlAttribute(name = "id")
    private Integer id;

    @XmlAttribute(name = "parentId")
    @Transient
    private Integer parentId;

    @XmlElement(name = "category")
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

package com.example.co2Automatic.models;

import com.example.co2Automatic.HelpUtils.CustomExceptions.ImpossibleAmountDecreasingException;
import com.example.co2Automatic.models.HelpModels.Image;
import com.example.co2Automatic.models.ModelEnums.MoneyCurrency;
import com.example.co2Automatic.models.HelpModels.ProductParam;
import com.example.co2Automatic.models.ModelEnums.ProductStock;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Data
@Entity
@Table(name = "products")
@JsonIgnoreProperties(value = {"productUrlFromExternalResource",
        "description",
        "params",
        "productCategory",
        "countryOfOrigin",
        "creationDate",
        "lastUpdatingDate",
        "vendor",
        "orderLines"
})
public class Product implements Serializable {

    public Product() {

        creationDate = LocalDate.now();

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "product_url_from_external_resource")
    private String productUrlFromExternalResource;

    @Column(name = "quantity")
    @Min(0)
    private int quantity ;

    @Column(name = "moneyCurrency")
    @Enumerated(EnumType.STRING)
    private MoneyCurrency currency;

    //    @Lob
//    @Basic(fetch = FetchType.LAZY)
    @Column(name = "description", length = 20000)
    private String description;

    @Transient
    private int categoryXmlId;

//    @ElementCollection(targetClass = String.class)
//    @CollectionTable(name = "product_params")
//    @MapKeyColumn(name = "product_param_name")
//    @Column(name = "params")
//    private Map<String, String> params = new HashMap<>();

    @OneToMany(cascade = CascadeType.ALL,
    orphanRemoval = true)
    @Column(name = "product_params")
    private List<ProductParam> params = new ArrayList<>();

    @Column(name = "price")
    private double price;

    @Column(name = "wholesale_price")
    private double wholeSalePrice;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory productCategory;

    @Column(name = "country_of_origin")
    private String countryOfOrigin;

    @Column(name = "product_stock")
    @Enumerated(EnumType.STRING)
    private ProductStock productStock = ProductStock.NO_STOCK;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    @Column(name = "last_updating_date")
//    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatingDate;

//    @ElementCollection
//    @CollectionTable(name = "urls")
    @Column(name = "image_urls")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    @Column(name = "vendor")
    private String vendor;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Set<OrderLine> orderLines = new LinkedHashSet<>();

    @Column(name = "available")
    private boolean available;

    private Set<OrderLine> getOrderLines() {
        return Collections.unmodifiableSet(orderLines);
    }

    public void increaseAmount(int quantity) {
        this.quantity += quantity;
    }

    public void decreaseAmount(int quantity) throws ImpossibleAmountDecreasingException {

        if(this.quantity-quantity<0){
            throw new ImpossibleAmountDecreasingException("");
        }
            else {
                this.quantity -= quantity;
        }
    }

    //    @ManyToMany(mappedBy = "products")
//    private List<Order> ordersList;
    @PreUpdate
    @PrePersist
    protected void modifyLastUpdatingDate() {
        if (quantity > 0) {
            available = true;
        } else available = false;
        lastUpdatingDate = new Date();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public void removeOrderLine(OrderLine orderLine) {
        this.quantity += orderLine.getAmount();
        orderLines.remove(orderLine);
    }

    public void addOrderLine(OrderLine orderLine) {
        this.quantity -= orderLine.getAmount();
        orderLines.add(orderLine);
    }

}

package com.example.prj04_hibernate_spring_data_security_sklep.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;


/**
 * The persistent class for the products database table.
 *
 */
@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="product_id", updatable=false)
    private Integer productId;

    private String description;

    @NotNull
    @Max(99_999_999)  //typ long
    private BigDecimal price;


    @Column(name="product_name")
    @NotNull //productName nie moze byc nullem i to jest zapisane w javie a nie postgresie
    //@Size(min = 3, max = 10) //mozna to wykorzystac dla stringow tablic list i map
    private String productName;

    @DecimalMin("0.00")
    @DecimalMax("0.99")
    private BigDecimal vat;


    public Product() {
    }

    public Integer getProductId() {
   	 return this.productId;
    }

    public void setProductId(Integer productId) {
   	 this.productId = productId;
    }

    public String getDescription() {
   	 return this.description;
    }

    public void setDescription(String description) {
   	 this.description = description;
    }

    public BigDecimal getPrice() {
   	 return this.price;
    }

    public void setPrice(BigDecimal price) {
   	 this.price = price;
    }

    public String getProductName() {
   	 return this.productName;
    }

    public void setProductName(String productName) {
   	 this.productName = productName;
    }

    public BigDecimal getVat() {
   	 return this.vat;
    }

    public void setVat(BigDecimal vat) {
   	 this.vat = vat;
    }

}


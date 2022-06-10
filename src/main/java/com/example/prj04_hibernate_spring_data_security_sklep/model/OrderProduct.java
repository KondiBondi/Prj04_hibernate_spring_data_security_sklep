package com.example.prj04_hibernate_spring_data_security_sklep.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the order_products database table.
 *
 */
@Entity
@Table(name="order_products")
@NamedQuery(name="OrderProduct.findAll", query="SELECT o FROM OrderProduct o")
public class OrderProduct implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private OrderProductPK id;

    @Column(name="actual_price")
    private BigDecimal actualPrice;

    @Column(name="actual_vat")
    private BigDecimal actualVat;

    private int quantity;

    //bi-directional many-to-one association to Order
    @ManyToOne
    @JoinColumn(name="order_id", insertable=false, updatable=false)
    private Order order;

    //uni-directional many-to-one association to Product
    @ManyToOne
    @JoinColumn(name="product_id", insertable=false, updatable=false)
    private Product product;

    public OrderProduct() {
    }

    public OrderProductPK getId() {
   	 return this.id;
    }

    public void setId(OrderProductPK id) {
   	 this.id = id;
    }

    public BigDecimal getActualPrice() {
   	 return this.actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
   	 this.actualPrice = actualPrice;
    }

    public BigDecimal getActualVat() {
   	 return this.actualVat;
    }

    public void setActualVat(BigDecimal actualVat) {
   	 this.actualVat = actualVat;
    }

    public int getQuantity() {
   	 return this.quantity;
    }

    public void setQuantity(int quantity) {
   	 this.quantity = quantity;
    }

    public Order getOrder() {
   	 return this.order;
    }

    public void setOrder(Order order) {
   	 this.order = order;
    }

    public Product getProduct() {
   	 return this.product;
    }

    public void setProduct(Product product) {
   	 this.product = product;
    }

}


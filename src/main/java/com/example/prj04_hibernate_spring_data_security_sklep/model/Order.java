package com.example.prj04_hibernate_spring_data_security_sklep.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the orders database table.
 *
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="order_id", updatable=false)
    private Integer orderId;

    @Temporal(TemporalType.DATE)
    @Column(name="delivery_date")
    private Date deliveryDate;

    @Column(name="order_date")
    private Timestamp orderDate;

    @Temporal(TemporalType.DATE)
    @Column(name="planned_delivery_date")
    private Date plannedDeliveryDate;

    private String status;

    //bi-directional many-to-one association to OrderProduct
    @OneToMany(mappedBy="order")
    private List<OrderProduct> orderProducts;

    //bi-directional many-to-one association to Customer
    @ManyToOne
    @JoinColumn(name="customer_email")
    private Customer customer;

    public Order() {
    }

    public Integer getOrderId() {
   	 return this.orderId;
    }

    public void setOrderId(Integer orderId) {
   	 this.orderId = orderId;
    }

    public Date getDeliveryDate() {
   	 return this.deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
   	 this.deliveryDate = deliveryDate;
    }

    public Timestamp getOrderDate() {
   	 return this.orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
   	 this.orderDate = orderDate;
    }

    public Date getPlannedDeliveryDate() {
   	 return this.plannedDeliveryDate;
    }

    public void setPlannedDeliveryDate(Date plannedDeliveryDate) {
   	 this.plannedDeliveryDate = plannedDeliveryDate;
    }

    public String getStatus() {
   	 return this.status;
    }

    public void setStatus(String status) {
   	 this.status = status;
    }

    public List<OrderProduct> getOrderProducts() {
   	 return this.orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
   	 this.orderProducts = orderProducts;
    }

    public OrderProduct addOrderProduct(OrderProduct orderProduct) {
   	 getOrderProducts().add(orderProduct);
   	 orderProduct.setOrder(this);

   	 return orderProduct;
    }

    public OrderProduct removeOrderProduct(OrderProduct orderProduct) {
   	 getOrderProducts().remove(orderProduct);
   	 orderProduct.setOrder(null);

   	 return orderProduct;
    }

    public Customer getCustomer() {
   	 return this.customer;
    }

    public void setCustomer(Customer customer) {
   	 this.customer = customer;
    }

}


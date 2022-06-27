package com.example.prj04_hibernate_spring_data_security_sklep.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;


/**
 * The persistent class for the customers database table.
 *
 */
@Entity
@Table(name="customers")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="customer_email")
    private String customerEmail;

    private String address;

    private String city;

    private String name;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="postal_code")
    @Pattern(regexp="\\d{2}-\\d{3}")   //wzor w psotaci jakiej ma byc zapisany kod pocztowy
    private String postalCode;

    //bi-directional many-to-one association to Order
    @OneToMany(mappedBy="customer")
    private List<Order> orders;

    public Customer() {
    }

    public String getCustomerEmail() {
   	 return this.customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
   	 this.customerEmail = customerEmail;
    }

    public String getAddress() {
   	 return this.address;
    }

    public void setAddress(String address) {
   	 this.address = address;
    }

    public String getCity() {
   	 return this.city;
    }

    public void setCity(String city) {
   	 this.city = city;
    }

    public String getName() {
   	 return this.name;
    }

    public void setName(String name) {
   	 this.name = name;
    }

    public String getPhoneNumber() {
   	 return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
   	 this.phoneNumber = phoneNumber;
    }

    public String getPostalCode() {
   	 return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
   	 this.postalCode = postalCode;
    }

    public List<Order> getOrders() {
   	 return this.orders;
    }

    public void setOrders(List<Order> orders) {
   	 this.orders = orders;
    }

    public Order addOrder(Order order) {
   	 getOrders().add(order);
   	 order.setCustomer(this);

   	 return order;
    }

    public Order removeOrder(Order order) {
   	 getOrders().remove(order);
   	 order.setCustomer(null);

   	 return order;
    }

}


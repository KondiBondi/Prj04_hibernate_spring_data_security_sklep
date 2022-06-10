package com.example.prj04_hibernate_spring_data_security_sklep.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the order_products database table.
 *
 */
@Embeddable
public class OrderProductPK implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name="order_id", insertable=false, updatable=false)
    private Integer orderId;

    @Column(name="product_id", insertable=false, updatable=false)
    private Integer productId;

    public OrderProductPK() {
    }
    public Integer getOrderId() {
   	 return this.orderId;
    }
    public void setOrderId(Integer orderId) {
   	 this.orderId = orderId;
    }
    public Integer getProductId() {
   	 return this.productId;
    }
    public void setProductId(Integer productId) {
   	 this.productId = productId;
    }

    public boolean equals(Object other) {
   	 if (this == other) {
   		 return true;
   	 }
   	 if (!(other instanceof OrderProductPK)) {
   		 return false;
   	 }
   	 OrderProductPK castOther = (OrderProductPK)other;
   	 return
   		 this.orderId.equals(castOther.orderId)
   		 && this.productId.equals(castOther.productId);
    }

    public int hashCode() {
   	 final int prime = 31;
   	 int hash = 17;
   	 hash = hash * prime + this.orderId.hashCode();
   	 hash = hash * prime + this.productId.hashCode();
   	 
   	 return hash;
    }
}


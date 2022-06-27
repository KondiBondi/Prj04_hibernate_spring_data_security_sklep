package com.example.prj04_hibernate_spring_data_security_sklep.basket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

//czasami na takie klasy sie mowi DTO - dodatkowa klasa ktra nei odwpowiada bazie danych jeden do jednego
public class ProductInBasket {
    private int productId;
    private String productName;
    private BigDecimal price;
    private int count;
    
    public int getProductId() {
   	 return productId;
    }

    public void setProductId(int productId) {
   	 this.productId = productId;
    }

    public String getProductName() {
   	 return productName;
    }

    public void setProductName(String name) {
   	 this.productName = name;
    }

    public BigDecimal getPrice() {
   	 return price;
    }

    public void setPrice(BigDecimal price) {
   	 this.price = price;
    }

    public int getCount() {
   	 return count;
    }

    public void setCount(int count) {
   	 this.count = count;
    }

    @Override
    public String toString() {
   	 return "ProductInBasket [productId=" + productId + ", name=" + productName + ", price=" + price + ", count=" + count + "]";
    }

    @Override
    public int hashCode() {
   	 return Objects.hash(count, productName, price, productId);
    }

    @Override
    public boolean equals(Object obj) {
   	 if (this == obj)
   		 return true;
   	 if (obj == null)
   		 return false;
   	 if (getClass() != obj.getClass())
   		 return false;
   	 ProductInBasket other = (ProductInBasket) obj;
   	 return count == other.count && Objects.equals(productName, other.productName) && Objects.equals(price, other.price)
   			 && productId == other.productId;
    }

    public void increment(int n) {
   	 this.count += n;
    }
    
    public BigDecimal getValue() {
   	 return this.getPrice().multiply(BigDecimal.valueOf(this.count)).setScale(2, RoundingMode.HALF_UP);
    }
    
}




package com.example.prj04_hibernate_spring_data_security_sklep.basket;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import com.example.prj04_hibernate_spring_data_security_sklep.model.Product;

public class Basket {
    private Map<Integer, ProductInBasket> products = new TreeMap<>();
    
    public Collection<ProductInBasket> getProducts() {
   	 return Collections.unmodifiableCollection(products.values());
    }

    public void incrementProduct(Product product) {
   	 if(products.containsKey(product.getProductId())) {
   		 ProductInBasket pib = products.get(product.getProductId());
   		 pib.increment(1);
   	 } else {
   		 ProductInBasket pib = new ProductInBasket();
   		 pib.setProductId(product.getProductId());
   		 pib.setProductName(product.getProductName());
   		 pib.setPrice(product.getPrice());
   		 pib.setCount(1);
   		 products.put(pib.getProductId(), pib);
   	 }
    }
    
    public BigDecimal getBasketValue() {
   	 return products.values().stream()
   		 .map(ProductInBasket::getValue)
   		 .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}




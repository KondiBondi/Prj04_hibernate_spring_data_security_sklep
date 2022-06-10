package com.example.prj04_hibernate_spring_data_security_sklep.controller;

import java.util.List;

import javax.persistence.EntityManager;

import com.example.prj04_hibernate_spring_data_security_sklep.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/products-em")
public class ProductControllerEM {
	// W ramach aplikacji Springowej można bezpośrednio korzystać z EntityManagera i
	// technologii Hibernate/Persistence.
	// ale lepsze rozwiązanie, oparte o interfejsy i repozytoria zobaczycie w innych
	// miejscach aplikacji.
	@Autowired
	private EntityManager em;

	@GetMapping
	public String allProducts(Model model) {
    	List<Product> products = em.createNamedQuery("Product.findAll", Product.class).getResultList();

    	model.addAttribute("products", products);
    	return "products";
	}

	@GetMapping("/{id}")
	public String oneProduct(Model model, @PathVariable("id") int productId) {
    	Product product = em.find(Product.class, productId);
    	if(product != null) {
        	model.addAttribute("product", product);
        	return "product";
    	} else {
        	model.addAttribute("id", productId);
        	return "missing_product";
    	}
	}

}




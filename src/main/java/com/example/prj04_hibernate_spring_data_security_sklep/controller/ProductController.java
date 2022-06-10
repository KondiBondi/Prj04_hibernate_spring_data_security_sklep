package com.example.prj04_hibernate_spring_data_security_sklep.controller;

import com.example.prj04_hibernate_spring_data_security_sklep.model.Product;
import com.example.prj04_hibernate_spring_data_security_sklep.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository repository;

    @GetMapping
    public String allProducts(Model model) {
        List<Product> products = repository.findAll();
        model.addAttribute("products", products);
        return "products";   //bez prefixu i suffixu
    }

    @GetMapping("/{id}") //dodajemy dalsz czesc adresu do products - localhost:8080/products/1
    //przy takim zapisie to co jest za ostatnim slashem jest traktowane jako parametr oznaczony slowem id
    public String oneProduct(Model model,
                             @PathVariable("id") int productId) { //wartosc wczytana z URL zostanie wpisana jako zmienna productId
        Optional<Product> product = repository.findById(productId); //optional product znaczy ze jezeli cos istnieje to to zwroci a jak nie to
        //zwroci jakby puste pudelko
        if (product.isPresent()) {  //sprawdzamy czy w zwróconym "pudełku" cos jest
            // jeśli udało się znaleźć produkt, to obiekt dodajemu do modelu i przechodzimy do strony produktu

            model.addAttribute("product", product.get());
            return "product";
        } else {
            // jeśli produktu o takim ID nie ma, to wyświetlam info o braku produktu

            model.addAttribute("id", productId);
            return "missing_product";
        }
    }
}

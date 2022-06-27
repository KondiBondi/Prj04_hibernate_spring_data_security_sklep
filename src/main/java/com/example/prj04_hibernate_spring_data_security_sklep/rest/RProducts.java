package com.example.prj04_hibernate_spring_data_security_sklep.rest;

import com.example.prj04_hibernate_spring_data_security_sklep.model.Product;
import com.example.prj04_hibernate_spring_data_security_sklep.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class RProducts {  // r for resources bo sie mowi ze zasoby udsotepnia

    @Autowired
    private ProductRepository repository;

    @GetMapping("/rest/products")
    @ResponseBody
    public List<Product> readAll() {
        return repository.findAll();
    }

    @GetMapping("/rest/products/{id}")
    @ResponseBody
    public Product readOne(@PathVariable("id") Integer productId) { //jak jest parametr typu prostego - int to jest obowiazkowy
        //Integer jest opcjonalny.  w tym konrketnym przypadku bez roznicy
        return repository.findById(productId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        //mozna uzyc .get() ale wtedy wywali blad jezeli bedzie niepoprawny nr
        //dzieki temu ze jest to w formie lambdy to wyajtek ebdzie utworzony dopiero keidy ebdzie to ptorzebne
        //supplier - po prawej stronie wynik, cos co sie tworzy a po lewej pste nawiasy
    }
}

package com.example.prj04_hibernate_spring_data_security_sklep.rest;

import com.example.prj04_hibernate_spring_data_security_sklep.model.Product;
import com.example.prj04_hibernate_spring_data_security_sklep.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController  //jezeli wpiszemy tutaj adnotacje @RestController zamiast @Controller to nie musimy dopisywac @ResponseBody
//bo wtedy juz kazde bedzie autoamtycznie odsylalo tresc a nei widok/szablonow. Automatycznie tak czy siak odsyla JSONa
//dziala to tak jakby kazda metoda miala dopisane ResponseBody
@RequestMapping("/rest/products")       //Najczęściej na poziomie klasy umieszcza się też @RequestMapping z ogólnym
// adresem, pod którym działa cała ta klasa.
       // * Metody wewnątrz mogą mieć podany "dalszy ciąg adresu".
public class RProducts {  // r for resources bo sie mowi ze zasoby udsotepnia

    @Autowired
    private ProductRepository repository;

    //@GetMapping("/rest/products")
    @GetMapping
    //@ResponseBody     - nie potrzebne bo zuywamy RestController jako adnotacji nad klasą
    public List<Product> readAll() {
        return repository.findAll();
    }

    //@GetMapping("/rest/products/{id}")
    @GetMapping("/{id}")
    //@ResponseBody
    public Product readOne(@PathVariable("id") Integer productId) { //jak jest parametr typu prostego - int to jest obowiazkowy
        //Integer jest opcjonalny.  w tym konrketnym przypadku bez roznicy

        //linijka ponizej jest najbardziej profesjonalna
        //return repository.findById(productId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        //mozna uzyc .get() ale wtedy wywali blad jezeli bedzie niepoprawny nr
        //dzieki temu ze jest to w formie lambdy to wyajtek ebdzie utworzony dopiero keidy ebdzie to ptorzebne
        //supplier - po prawej stronie wynik, cos co sie tworzy a po lewej pste nawiasy


        //ponizej ejst to co znajduje sie/dziala pod linijka z lambda (linia 34):
        Optional<Product> product = repository.findById(productId);
        if(product.isPresent()) {

            return product.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Brak produktu nr " + productId);
        }

    }
}

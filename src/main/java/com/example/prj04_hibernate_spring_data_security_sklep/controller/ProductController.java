package com.example.prj04_hibernate_spring_data_security_sklep.controller;

import com.example.prj04_hibernate_spring_data_security_sklep.model.Product;
import com.example.prj04_hibernate_spring_data_security_sklep.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
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

    @GetMapping("/find")
    public String search(Model model,
                         @RequestParam(name = "name", defaultValue = "") String name,
                         @RequestParam(name = "min", defaultValue = "0") BigDecimal min,
                         @RequestParam(name = "max", defaultValue = "1000000000") BigDecimal max) {

        List<Product> products = List.of();

        if (name == null || name.isEmpty()) {
            products = repository.findByPriceBetween(min, max); //tworzymy te dwie metody w interfejsie i nei musimy ich deklarowac a Spring je utworzy!
        } else {
            products = repository.findByProductNameContainingAndPriceBetween(name, min, max);
        }
        model.addAttribute("products", products);
        return "wyszukiwarka";
    }

    @GetMapping("/new")
    public String newProduct(Product product) { //ze wzgledu na odwolnie do obiektu product w formularzu modelAttribute = "product",
        //musimy utowrzyc pusty oibiekt. wystarczy ten obiekt podac w atrybutach
        //wyswietlenie pustego formularza
        return "product_form";
    }

    @GetMapping("/{id}/edit") //dzieki adnotacji PathVariable ponizej mozemy przechwycic parametr ze sciezki i przekazac na zmienną
    public String editProduct(Model model, @PathVariable("id") int productId) {

//dzialanie ponizszego kodu jest opisane powyzej w metodzie oneProduct
        Optional<Product> product = repository.findById(productId);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "product_form";
        } else {
            model.addAttribute("id", productId);
            return "missing_product";
        }


    }

    //zeby ubsluzyc tablice adresow czyli wiecej niz jeden adres trzeba uzyc nawiasow klamrowych
    @PostMapping ({"/new", "/{id}/edit"})
    public String saveProduct(
            //model w ostatecznej wersji nie ejst potrzebny bo bledy wyswietla f:errors
            //Model model, //z tego mozna zrezygnowac bo product to obiekt automatycznie tworzony przez Spring wiec a jednak pozniej
            //trzeba bylo dodac zeby skorzystac z BindingResults
            //jest czescia modelu - jest tez adnotacja ale nie trzeba jej pisac - model attribute czy jakos tak
           @Valid Product product, //dzieki ten adnotacji f:errors w jsp wie ze ma wyswietlic error
                BindingResult bindingResult     //musi byc zeby metoda sie wykonala mimo bledow bo inaczej wywali sie
            //stronka zanim pojawia sie komunikaty o bledach - f:errors w jsp
                ) {
        //Product product zamiast oddzielnie kazde pole/parametr
        //spring podstawia parametry z formularza, wystarczy je dodac jako parametry do metody
        //mzona dodac adnotacje @RequestParam daje kontrole nad szczegolami ale domyslnie, bez zadnej adnotacji
        //spring po prostu wstawia dane z formularza

        //adnotacja @Valid - jesli rpzed parametrem znajduje sie ta adnotacja to obiekt jest validowany przed wywolanie
        //metody przez springa i jesli metoda nie posiada parametru BindingResult, to w sytuacji gdy obiekt jest bledny
        // spring w ogole nie wywola tej metody

        if (bindingResult.hasErrors()) {
            //sa bledy, nie zapisujemy obiektu i pozostajemy na stronie formularza
            //model.addAttribute("errors", bindingResult.getAllErrors());
            System.out.println("Errory");
        } else {

            //jesli nei ma bledow to zapisujemy do repozytaorium
            //gdyby byly bledy walidacji to save nie zapisalby takiego obiektu
            System.out.println("Proba zapisania obiektu, product id = " + product.getProductId());
            repository.save(product);
            System.out.println("Zapisano obiekt, product id = " + product.getProductId());
            //model.addAttribute("zapisano", true);  //uzywamy w ifie w pliku jsp zeby sprawdzic
            // czy wyswietlic bledy
            //czy nie
            //jak zapiszemy to idziemy an strone z informacjami o danym produkcie:
            return "redirect:/products/" + product.getProductId();
        }

        //System.out.println("Proba zapisania obiektu product id = " + product.getProductId()); //to tylko dla nas zebysmy wiedzieli
        //kiedy i co sie dzieje

        //jesli w klasie Modelu (product) sa adnotacje dotyczace validacji to podczas proby zapisania obiektu nie spelniajacego wymagan dochodzi
        //do bledu w operacji save

        //repository.save(product);
        //model.addAttribute("product", product); //dlaczego nie potrzebne jest wyjasnienie pod nazwa metody
        return "product_form";
    }

}

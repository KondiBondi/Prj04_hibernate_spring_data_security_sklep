package com.example.prj04_hibernate_spring_data_security_sklep.rest;

import com.example.prj04_hibernate_spring_data_security_sklep.model.Product;
import com.example.prj04_hibernate_spring_data_security_sklep.photo.PhotoUtl;
import com.example.prj04_hibernate_spring_data_security_sklep.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
//jezeli wpiszemy tutaj adnotacje @RestController zamiast @Controller to nie musimy dopisywac @ResponseBody
//bo wtedy juz kazde bedzie autoamtycznie odsylalo tresc a nei widok/szablonow. Automatycznie tak czy siak odsyla JSONa
//dziala to tak jakby kazda metoda miala dopisane ResponseBody
@RequestMapping("/rest/products")       //Najczęściej na poziomie klasy umieszcza się też @RequestMapping z ogólnym
// adresem, pod którym działa cała ta klasa.
// * Metody wewnątrz mogą mieć podany "dalszy ciąg adresu".
public class RProducts {  // r for resources bo sie mowi ze zasoby udsotepnia

    @Autowired
    private ProductRepository repository;

    @Autowired
    private PhotoUtl photoUtil;


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
        if (product.isPresent()) {

            return product.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Brak produktu nr " + productId);
        }

    }

    /* Tylko jako przykład możliwości: odczyt wybranego pola z rekordu.
     * To twórca usługi decyduje o tym, jakie adresy będą dostępne i co pod nimi będzie.
     * Jeśli uważamy, że klientom "przyda się" bezpośredni dostęp do wybranego pola, to możemy stworzyć taką metodę,
     * ale nie ma żadnego obowiązku, aby robić to dla wszystkich pól.
     */
    @GetMapping(path = "/{id}/price", produces = "application/json")  //produces - co ma zwrocic
    public BigDecimal getPrice(@PathVariable("id") Integer productId) {
        Optional<Product> product = repository.findById(productId);
        if (product.isPresent()) {
            return product.get().getPrice();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Brak produktu nr " + productId);
        }
    }


    /* Operacja PUT służy do zapisania danych POD PODANYM ADRESEM.
     * Na przykład PUT products/2/price z wartością 100 powinno ustawić w produkcie nr 2 cenę 100.
     * Jeśli PUT zadziała, to następnie GET wysłany pod ten sam adres powinien odczytać te same dane, które PUT zapisał.
     * (być może w innym formacie - to inny temat)
     *
     * PUT najczęściej jest używany do aktualizacji istniejących danych (pojedynczych wartości albo całych rekordów),
     * ale może być też użyty do zapisania nowych danych.
     * To, co najważniejsze, to fakt, że PUT zapisuje dane pod konkretnym adresem, do którego jest wysyłany.
     */


    @PutMapping(path = "/{id}/price", produces = "application/json")  //produces - co ma zwrocic
    public void setPrice(
            @PathVariable("id") Integer productId,
            @RequestBody BigDecimal newPrice  //parametr żądania
    ) {
        Optional<Product> optionalProduct = repository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setPrice(newPrice);
            repository.save(product);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Brak produktu nr " + productId);
        }
    }

    /* PUT może również służyć do zapisania całego rekordu,
     * ale zwn, że musi być skierowany pod ten adres, pod którym rekord zostanie faktycznie zapisany,
     * w praktyce PUT jest uzywany do aktualizacji rekordów (UPDATE).
     */
    @PutMapping("/{id}")
    public void update(@PathVariable("id") Integer productId, @RequestBody Product product) {
        // Aby mieć pewność, że zapisujemu produkt o typ ID, wpisuję productId z URL-a.
        // Ewentualnie możnaby jeszcze sprawdzić czy rekord istnieje, czy ID się zgadza i jeśli coś jest nie tak, to wyrzucić wyjątek.
        product.setProductId(productId);
        repository.save(product);
    }

    /* POST jest najbardziej ogólną metodą HTTP; oznacza, że klient "wysyła jakieś dane na serwer",
     * a serwer odsyła jakąś odpowiedź. W zasadzie POST może słuzyć do wszystkiego.
     *
     * W praktyce POST bardzo często służy do dodawania nowych rekordów, ponieważ gdy tworzymy nowy rekord,
     * to nie znamy z góry jego ID i nie wiemy pod jakim URL-em zostanie zapisany (nie da się więc użyć PUT).
     * Stąd wzięła REST-owa konwencja, że aby dodać nowy rekord do katalogu,
     * wysyła się POST z danymi tego rekordu pod ogólny adres całego katalogu.
     */
    @PostMapping
    public Product insert(@RequestBody Product product) {
        // Aby mieć pewność, że zapytanie tworzy nowy rekord, ustawwiam productId na null.
        product.setProductId(null);
        repository.save(product);
    	/* Operacja save (a wewnętrznie persist z Hibernate) spowoduje ustawienie nowego ID w obiekcie.
       	Warto taką informację przekazać klientowi.
       	Można:
       	1) odesłać uzupełniony rekord (i tak zrobimy tutaj),
       	2) odesłać "małego JSON-a" z informacją o tym ID (i innymi informacjami, które serwer chce przekazać klientowi)
       	3) tylko nagłówek Location z URL-em nowego rekordu (to zobaczymy w wersji JAX-RS).
     	*/
        return product;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer productId) {
        try {
            repository.deleteById(productId);
        } catch (EmptyResultDataAccessException e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Brak produktu nr " + productId);
        }
    }

    //wiekszosc komunikacji w uslugach restowych odbywa sie w formacie json ale
    //czasami uzywany jest format xml. a dla niektorych danych stosujemy bezposrednio jakis format specjalny np png czy jpg
    @GetMapping(path="/{id}/photo", produces="image/jpeg")
    public byte[] getPhoto(
            @PathVariable("id") Integer productId) {
        return photoUtil.readPhoto(productId);
    }

}

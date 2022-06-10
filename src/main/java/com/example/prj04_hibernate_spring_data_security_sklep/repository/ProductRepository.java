package com.example.prj04_hibernate_spring_data_security_sklep.repository;

import com.example.prj04_hibernate_spring_data_security_sklep.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    /* Gdy w projekcie umieścimy interfejs rozszerzający interfejs JpaRepository (albo podobny)
     * i onzaczymy go adnotacją @Repository (albo skonfigurujemy w inny sposób...),
     * to Spring AUTMATYCZNIE UTWORZY IMPLEMENTACJĘ tego interfejsu.
     * Dzięki temu "za darmo" mamy metody dający podstawowy dostęp do tabel.
     * * Dodatkowo w interfejsie można dopisać własne metody, w których nazwie kryje się zasada działania.
     * Np. findByPriceBetween szuka produktów o cenie między min i max.
     *
     * findByEmail - szuka rekordów z polem email równym parametrowi.
     */




    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

    List<Product> findByProductNameContainingAndPriceBetween(String name, BigDecimal min, BigDecimal max);
    //nie trzeba ich deklarowac a Sprng je utworzy - programowanie zyczeniowe

}

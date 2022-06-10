package com.example.prj04_hibernate_spring_data_security_sklep.repository;

import com.example.prj04_hibernate_spring_data_security_sklep.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    /* Gdy w projekcie umieścimy interfejs rozszerzający interfejs JpaRepository (albo podobny)
     * i onzaczymy go adnotacją @Repository (albo skonfigurujemy w inny sposób...),
     * to Spring AUTMATYCZNIE UTWORZY IMPLEMENTACJĘ tego interfejsu.
     * Dzięki temu "za darmo" mamy metody dający podstawowy dostęp do tabel.
     */


}

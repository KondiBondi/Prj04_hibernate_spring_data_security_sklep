package com.example.prj04_hibernate_spring_data_security_sklep.photo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component //spring utworzy obiekt tej klasy, ebdzie go caly czas pameital i w innych czesciach kodu mozna sie bedzie do
//niego odwolac
public class PhotoUtl {

    //odczytanei wartosci z konfiguracji:
    @Value("${photo_dir}")
    private String photo_dir;

    public byte[] readPhoto(int productId) {

        String fileName = productId + ".jpg";
        Path file = Paths.get(photo_dir, fileName);

        try {
            return Files.readAllBytes(file); //zwaracamy tablice bajtow bo zdjecie to ciag bajtow
        } catch(IOException e) {
            //aby w dowolnym miejscu wymusic odeslanie odpowiedzi z bledem do klienta np 404 not found
            //mozna wyrzucic taki wyjatek
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Produktu o numerze " + productId + " nie posiada zdjęcia");
        }


    }

}

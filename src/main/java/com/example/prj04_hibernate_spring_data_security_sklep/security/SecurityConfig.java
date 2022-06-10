package com.example.prj04_hibernate_spring_data_security_sklep.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //przekreslenie oznacza ze element javy jest rpzestazaly i nie zalecany.
    // dziala ale nie zaleca sie go uzywac ze wzgledu na zmiany ktore moga nastpic
    // w rpzyszlosci. np ten element moze zostac skasowany
    //ten element jest deprecated

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Tymczasowo luzujemy reguły bezpieczeństwa: wszystkim użytownikom (również niezalogowanym) pozwalamy na dostęp do wszystkich stron.
        http.authorizeHttpRequests().anyRequest().permitAll();
    }


}

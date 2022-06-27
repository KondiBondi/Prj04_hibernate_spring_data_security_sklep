package com.example.prj04_hibernate_spring_data_security_sklep.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //przekreslenie oznacza ze element javy jest rpzestazaly i nie zalecany.
    // dziala ale nie zaleca sie go uzywac ze wzgledu na zmiany ktore moga nastpic
    // w rpzyszlosci. np ten element moze zostac skasowany
    //ten element jest deprecated

    //autentykacja to nei ejst autoryzacja
    //autentykacja to spawdzenie czy ktos jest tym za kogo sie podaje, poprzez haslo albo odcisk palca np
    //autoryzacja to ustalenie kto ma dostep do czego. reguly dostepu

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //ta metoda sluzy ogolnej konfiguracji zabezpieczen aplikacji webowej i m.in. podaje sie tutaj reguly
        //autoryzacji/dostepu

        //piszemy do jakich adresow dopuszczani sa jacy uzytkownicy
        http.authorizeHttpRequests()
                //.anyRequest().permitAll() //kazdy mial prawo wyslac dowolne zapytanie

                .antMatchers("/products/new", "/products/*/edit").hasAuthority("ROLE_manager") // tylko manager może edytować
                .antMatchers("/customers/new", "/customers/*/edit").hasAuthority("ROLE_manager")
                .antMatchers("/products/find").authenticated() // zalogowany jako ktokolwiek może wyszukiwać
                .antMatchers("/", "/whoami", "/products/**", "/customers/**", "/*.css").permitAll() // dostęp dla wszystkich
                .antMatchers("/products?", "/products?/**").permitAll() // inne wersje listy produktów
                .antMatchers("/rest/**").permitAll()
                // .antMatchers("/login").anonymous() // nie może być zalogowany! - ale to przestało działać...
                .antMatchers("/login").permitAll()
                .antMatchers("/logout").authenticated() // zalogowany jako ktokolwiek
                .anyRequest().denyAll() // pozostałe adresy blokujemy


                .and().formLogin()//spring automatycznie generuje stronke login logout
                .and().csrf().disable(); //wylaczamy zabezpieczenie csrf ktore wymaga aby w kazdym POST byl token wczesniej wygenerowany przez aplikację

    }

    //aby zdefiniowac ko nta uzytkownikow trzeb utworzyc kolejna metode:

    //definuje uzytkownikow tej aplikacji:
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //w tej wersji ebzposrednio podajemy liste zuytkownikow w kodzie
        auth.inMemoryAuthentication()
                .withUser("ala")
                .password("{noop}kot")
                .roles("manager", "sprzedawca")
                .and().withUser("ola")
                .password("{noop}abc123").roles("sprzedawca")
                .and().withUser("ula").password("{noop}abc123").roles();

        // O co chodzi z noop?
        // Hasła mogą być "zaszyfrowane"/"zakodowane" na różne sposoby, aby nie było widać haseł bezpośrednio.
        // Algorytmy, za pomocą których hasła są "ukrywane", podaje się w nawiasach klamrowych ma początku stringa.
        // noop oznacza brak jakiejkolwiek konwersji

    }


}

package com.example.prj04_hibernate_spring_data_security_sklep.basket;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //ta adnotacja mowi springowi: na starcie aplikacji spring ma utworzyc obiekt tej klasy. zrobi to jednorazowo.
//gdy ten obiekt juz utworzy to przeglada wszystkie metody na tym obiekcie i jak znajdzie metode oznaczona jako @Bean to
//wywola te metode i wszystkie inne onzaczone jako @Bean
//wynik uruchomienia tych beanow jest zapamietywany jako obiekt typu komponent do pozniejszego zuycia
//jak spring zobaczyczy ze metoda zwraca HttpSessionListener to bean bedzie obserwowal czy tworzone sa nowe sesje
//z kazda sesja tworzony jest nowy koszyk dzieki temu

public class BasketConfiguration {
    // Tworzymy session listenera i dostarczamy springowi w metodzie oznaczonej @Bean
    // (alternatywnie można wymienić w pliku beans.xml)
    
    // Gdy Spring widzi klasę oznaczoną @Configuration i w tej klasie są metody oznaczone @Bean,
    // to na starcie aplikacji Spring:
    // 1) tworzy obiekt klasy Configuration
    // 2) uruchamia metody oznaoczne @Bean, a wyniki tych metod rejestruje jako "beany", czyli obiekty zarządzane,
    //	które można potem wstrzykiwać itp.
    
    // W tym przypadku obiekt jest typu HttpSessionListener i dzięki temu Spring od razu wie, do czego on służy./
    // Wie, że ma go użyć jako session listenera.
    @Bean
    public HttpSessionListener createSessionListener() {
   	 
   	 return new HttpSessionListener() {
   		 @Override
   		 public void sessionCreated(HttpSessionEvent se) {
   			 HttpSession session = se.getSession();
   			 session.setAttribute("basket", new Basket());
   			 System.out.println("tworzę sesję nr " + session.getId());
   		 }
   		 
   		 @Override
   		 public void sessionDestroyed(HttpSessionEvent se) {
   			 HttpSession session = se.getSession();
   			 System.out.println("koniec sesji nr " + session.getId());
   		 }
   	 };
   	 
    }
    
}




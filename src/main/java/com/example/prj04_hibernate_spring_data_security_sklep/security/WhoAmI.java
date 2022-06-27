package com.example.prj04_hibernate_spring_data_security_sklep.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WhoAmI {

    @GetMapping("/whoami")
    public String whoAmI(Authentication authentication, Model model) {

        //sprawdzamy czy jestesmy zalogowani/uwierzytelnieni jakoktokolwiek
        //sprawdzamy czy authentication nie jest null to dla dodatkowego bezpieczenstwa
        //i czy uztytkownik jest uwrzytelniony
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("userName", authentication.getName());
            model.addAttribute("authorities", authentication.getAuthorities());
        }

        return "whoami";
    }
}

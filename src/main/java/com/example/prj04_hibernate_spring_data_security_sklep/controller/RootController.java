package com.example.prj04_hibernate_spring_data_security_sklep.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
public class RootController {

    @RequestMapping("/")
    public String index() {
        return "index"; //nie musimy podawac .jsp i pelnej sciezki bo dodalismy info o prefix i suffix do applicationProperties pliku
    }

    @RequestMapping("/time")
    public String time(Model model) {
        model.addAttribute("dt", LocalDateTime.now());
        return "time"; //ten plik nie mzoe byc w templates bo nie zostanie znaleziony
        //templates jest przygotowany z mysla o innych technlogiach
        //time.jsp powinno byc umieszczone w src main webapp
    }


    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {

        return "Hello world";
    }

}

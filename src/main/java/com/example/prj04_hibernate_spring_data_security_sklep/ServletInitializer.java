package com.example.prj04_hibernate_spring_data_security_sklep;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Prj04HibernateSpringDataSecuritySklepApplication.class);
    }

}

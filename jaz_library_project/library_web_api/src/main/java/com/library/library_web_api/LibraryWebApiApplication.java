package com.library.library_web_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class LibraryWebApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryWebApiApplication.class, args);
    }

}

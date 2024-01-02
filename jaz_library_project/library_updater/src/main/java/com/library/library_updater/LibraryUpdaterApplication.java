package com.library.library_updater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = "com.library")
public class LibraryUpdaterApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryUpdaterApplication.class, args);
    }

}

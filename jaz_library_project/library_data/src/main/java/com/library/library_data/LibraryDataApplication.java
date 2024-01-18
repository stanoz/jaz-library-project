package com.library.library_data;

import com.library.library_data.repositories.ICatalogData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class LibraryDataApplication implements CommandLineRunner {

    private final ICatalogData catalogData;

    public LibraryDataApplication(ICatalogData catalogData) {
        this.catalogData = catalogData;
    }
    public static void main(String[] args) {
        SpringApplication.run(LibraryDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        var author = new Author();
//        author.setName("J. R. R. Tolkien");
//        author.setYearOfBirth(1892);
//        catalogData.getAuthors().save(author);
//        boolean exists = catalogData.getBooks().existsById(1L);
//        System.out.println(exists);
//        catalogData.getBooks().deleteById(1L);
    }
}

package com.library.library_data.repositories;
import com.library.library_data.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long>{
    Author findByName(String name);
    boolean existsByName(String name);
}

package com.library.library_data.repositories;

import com.library.library_data.model.Bookshelves;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookshelvesRepository extends JpaRepository<Bookshelves, Long> {
    Bookshelves findByName(String name);
    boolean existsByName(String name);
}

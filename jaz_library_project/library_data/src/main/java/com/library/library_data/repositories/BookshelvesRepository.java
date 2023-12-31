package com.library.library_data.repositories;

import com.library.library_data.model.Bookshelves;
import com.library.library_data.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookshelvesRepository extends JpaRepository<Bookshelves, Long> {
}

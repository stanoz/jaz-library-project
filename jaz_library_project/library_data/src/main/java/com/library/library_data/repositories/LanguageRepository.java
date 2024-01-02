package com.library.library_data.repositories;

import com.library.library_data.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    Language findByName(String name);
}

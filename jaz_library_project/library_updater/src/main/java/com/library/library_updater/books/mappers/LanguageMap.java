package com.library.library_updater.books.mappers;

import com.library.library_data.model.Language;
import org.springframework.stereotype.Component;

@Component
public class LanguageMap implements IMap<String, Language>{
    @Override
    public Language mapToEntity(String name) {
        Language languageEntity = new Language();
        languageEntity.setName(name);
        return languageEntity;
    }
}

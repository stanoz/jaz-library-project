package com.library.library_updater.books.mappers;

import com.library.library_data.model.Bookshelves;
import org.springframework.stereotype.Component;

@Component
public class BookshelvesMap implements IMap<String, Bookshelves>{
    @Override
    public Bookshelves mapToEntity(String name) {
        Bookshelves bookshelvesEntity = new Bookshelves();
        bookshelvesEntity.setName(name);
        return bookshelvesEntity;
    }
}

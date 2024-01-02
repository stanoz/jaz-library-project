package com.library.library_updater.books.mappers;

import com.library.library_client.contract.AuthorDto;
import com.library.library_client.contract.BookDto;
import com.library.library_client.contract.BookImageDto;
import com.library.library_data.model.*;
import org.springframework.stereotype.Component;

@Component
public class Mapper implements IMapper{
    private final IMap<String, Bookshelves> bookshelvesMap;
    private final IMap<String, Language> languageMap;
    private final IMap<String, Subject> subjectMap;
    private final IMap<AuthorDto, Author> authorMap;
//    private final IMap<BookImageDto, BookImage> bookImageMap;
    private final IMap<BookDto, Book> bookMap;

    public Mapper(IMap<String, Bookshelves> bookshelvesMap,
                  IMap<String, Language> languageMap,
                  IMap<String, Subject> subjectMap,
                  IMap<AuthorDto, Author> authorMap,
//                  IMap<BookImageDto, BookImage> bookImageMap,
                  IMap<BookDto, Book> bookMap) {
        this.bookshelvesMap = bookshelvesMap;
        this.languageMap = languageMap;
        this.subjectMap = subjectMap;
        this.authorMap = authorMap;
//        this.bookImageMap = bookImageMap;
        this.bookMap = bookMap;
    }

    @Override
    public IMap<AuthorDto, Author> author() {
        return authorMap;
    }

//    @Override
//    public IMap<BookImageDto, BookImage> bookImage() {
//        return bookImageMap;
//    }

    @Override
    public IMap<BookDto, Book> book() {
        return bookMap;
    }

    @Override
    public IMap<String, Bookshelves> bookshelves() {
        return bookshelvesMap;
    }

    @Override
    public IMap<String, Language> language() {
        return languageMap;
    }

    @Override
    public IMap<String, Subject> subject() {
        return subjectMap;
    }
}

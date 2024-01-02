package com.library.library_data.repositories;

import org.springframework.stereotype.Repository;

@Repository
public class LibraryDataCatalog implements ICatalogData{
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
//    private final BookImageRepository bookImageRepository;
    private final BookshelvesRepository bookshelvesRepository;
    private final LanguageRepository languageRepository;
    private final SubjectRepository subjectRepository;

    public LibraryDataCatalog(BookRepository bookRepository,
                              AuthorRepository authorRepository,
//                              BookImageRepository bookImageRepository,
                              BookshelvesRepository bookshelvesRepository,
                              LanguageRepository languageRepository,
                              SubjectRepository subjectRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
//        this.bookImageRepository = bookImageRepository;
        this.bookshelvesRepository = bookshelvesRepository;
        this.languageRepository = languageRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public BookRepository getBooks() {
        return bookRepository;
    }

    @Override
    public AuthorRepository getAuthors() {
        return authorRepository;
    }

//    @Override
//    public BookImageRepository getBookImages() {
//        return bookImageRepository;
//    }

    @Override
    public BookshelvesRepository getBookshelves() {
        return bookshelvesRepository;
    }

    @Override
    public LanguageRepository getLanguages() {
        return languageRepository;
    }

    @Override
    public SubjectRepository getSubjects() {
        return subjectRepository;
    }
}

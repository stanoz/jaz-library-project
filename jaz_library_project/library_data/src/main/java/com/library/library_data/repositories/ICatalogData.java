package com.library.library_data.repositories;

public interface ICatalogData {
    AuthorRepository getAuthors();
    BookRepository getBooks();
//    BookImageRepository getBookImages();
    BookshelvesRepository getBookshelves();
    LanguageRepository getLanguages();
    SubjectRepository getSubjects();
}

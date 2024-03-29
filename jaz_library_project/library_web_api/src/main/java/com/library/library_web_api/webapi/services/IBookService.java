package com.library.library_web_api.webapi.services;

import com.library.library_client.contract.AuthorDto;
import com.library.library_client.contract.BookDto;
import com.library.library_web_api.webapi.contract.*;

import java.util.List;

public interface IBookService {
    List<BookDto> getAllBooks();
    BookDto getBookDetails(Long id);
    void editBook(BookDto book, Long id);
    List<AuthorDbDto> getAllAuthors();
    AuthorDto getAuthor(Long authorId);
    List<AuthorDbDto> getAllAuthorsFromBook(Long bookId);
    void editAuthor(AuthorDto authorDto, Long bookId, Long authorId);
    Long getAuthorId(String name, Long bookId);
    void deleteAuthor(Long bookId, Long authorId);
    void addAuthor(AuthorDto authorDto, Long bookId);
    List<SubjectDto> getAllSubjects();
    SubjectDto getSubject(Long subjectId);
    List<SubjectDto> getAllSubjectsFromBook(Long bookId);
    Long getSubjectId(String name, Long bookId);
    void editSubject(SubjectDto subjectDto, Long bookId);
    void deleteSubject(Long bookId, Long subjectId);
    void addSubject(SubjectDto subjectDto, Long bookId);
    List<LanguageDto> getAllLanguages();
    LanguageDto getLanguage(Long languageId);
    List<LanguageDto> getAllLanguagesFromBook(Long bookId);
    Long getLanguageId(String name, Long bookId);
    void editLanguage(LanguageDto languageDto, Long bookId);
    void deleteLanguage(Long bookId, Long languageId);
    void addLanguage(LanguageDto languageDto, Long bookId);
    List<BookshelvesDto> getAllBookshelves();
    BookshelvesDto getBookshelves(Long bookshelvesId);
    List<BookshelvesDto> getAllBookshelvesFromBook(Long bookId);
    Long getBookshelvesId(String name, Long bookId);
    void editBookshelves(BookshelvesDto bookshelvesDto, Long bookId);
    void deleteBookshelves(Long bookId, Long bookshelvesId);
    void addBookshelves(BookshelvesDto bookshelvesDto, Long bookId);
    void deleteBook(Long id);
    void addBook(NewBookDto newBookDto);
}

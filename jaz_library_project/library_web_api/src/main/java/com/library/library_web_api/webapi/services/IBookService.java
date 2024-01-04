package com.library.library_web_api.webapi.services;

import com.library.library_client.contract.AuthorDto;
import com.library.library_client.contract.BookDto;
import com.library.library_data.model.Book;
import com.library.library_web_api.webapi.contract.LanguageDto;
import com.library.library_web_api.webapi.contract.SubjectDto;

import java.util.List;

public interface IBookService {
    List<BookDto> getAllBooks();
    BookDto getBookDetails(Long id);
    void editBook(BookDto book, Long id);
    void editAuthor(AuthorDto authorDto, Long bookId, Long authorId);
    Long getAuthorId(String name, Long bookId);
    void deleteAuthor(Long bookId, Long authorId);
    void addAuthor(AuthorDto authorDto, Long bookId);
    Long getSubjectId(String name, Long bookId);
    void editSubject(SubjectDto subjectDto, Long bookId);
    void deleteSubject(Long bookId, Long subjectId);
    void addSubject(SubjectDto subjectDto, Long bookId);
    Long getLanguageId(String name, Long bookId);
    void editLanguage(LanguageDto languageDto, Long bookId);
    void deleteLanguage(Long bookId, Long languageId);
    void addLanguage(LanguageDto languageDto, Long bookId);
}

package com.library.library_web_api.webapi.services;

import com.library.library_client.contract.BookDto;
import com.library.library_data.model.Book;

import java.util.List;

public interface IBookService {
    List<BookDto> getAllBooks();
    BookDto getBookDetails(Long id);
    void editBook(BookDto book, Long id);
}

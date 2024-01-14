package com.library.library_web_api.webapi.validators;

import com.library.library_client.contract.AuthorDto;
import com.library.library_client.contract.BookDto;
import com.library.library_web_api.webapi.contract.NewBookDto;

public interface IValidate {
    boolean validateBook(BookDto bookDto);
    boolean validateNewBook(NewBookDto newBookDto);
    boolean validateAuthor(AuthorDto authorDto);
    boolean validateStringDto(String dtoNameField);
}

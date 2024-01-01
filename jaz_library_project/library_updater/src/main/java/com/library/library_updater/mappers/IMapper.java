package com.library.library_updater.mappers;

import com.library.library_client.contract.AuthorDto;
import com.library.library_client.contract.BookDto;
import com.library.library_client.contract.BookImageDto;
import com.library.library_data.model.*;

public interface IMapper {
    IMap<AuthorDto, Author> author();
    IMap<BookImageDto, BookImage> bookImage();
    IMap<BookDto, Book> book();
    IMap<String, Bookshelves> bookshelves();
    IMap<String, Language> language();
    IMap<String, Subject> subject();
}

package com.library.library_updater.books.mappers;

import com.library.library_client.contract.BookDto;
import com.library.library_data.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMap implements IMap<BookDto, Book>{
    @Override
    public Book mapToEntity(BookDto bookDto) {
        Book bookEntity = new Book();
        bookEntity.setSourceId(bookDto.getId());
        bookEntity.setTitle(bookDto.getTitle());
        bookEntity.setDownloadCount(bookDto.getDownloadCount());
        return bookEntity;
    }
}

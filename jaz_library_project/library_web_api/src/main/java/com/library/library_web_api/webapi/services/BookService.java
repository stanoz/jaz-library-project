package com.library.library_web_api.webapi.services;

import com.library.library_client.contract.AuthorDto;
import com.library.library_client.contract.BookDto;
import com.library.library_data.model.*;
import com.library.library_data.repositories.ICatalogData;
import com.library.library_updater.books.mappers.IMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService{

    private final ICatalogData db;
    private final IMapper mapper;

    @Override
    public List<BookDto> getAllBooks() {
        return db.getBooks().findAll().stream().map(BookService::getBookDto).toList().stream()
                .sorted(Comparator.comparingLong(BookDto::getId)).collect(Collectors.toList());
    }

    @Override
    public BookDto getBookDetails(Long id) {
        return db.getBooks().findById(id).map(BookService::getBookDto).orElse(null);
    }

    @Override
    public void editBook(BookDto bookDto, Long id) {
//        Book bookEntity = mapper.book().mapToEntity(bookDto);
//
//        db.getBooks().save(bookEntity);

        db.getBooks().findById(id).ifPresent(book -> {
            book.setTitle(bookDto.getTitle());
            book.setDownloadCount(bookDto.getDownloadCount());
            db.getBooks().save(book);
        });
    }

    private static BookDto getBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setAuthors(book.getAuthors().stream().map(author ->{
            AuthorDto authorDto = new AuthorDto();
            authorDto.setName(author.getName());
            authorDto.setYearOfBirth(author.getYearOfBirth());
            authorDto.setYearOfDeath(author.getYearOfDeath());
            return authorDto;
        }).collect(Collectors.toList()));
        bookDto.setBookshelves(book.getBookshelves().stream().map(Bookshelves::getName).collect(Collectors.toList()));
        bookDto.setLanguages(book.getLanguages().stream().map(Language::getName).collect(Collectors.toList()));
        bookDto.setSubjects(book.getSubjects().stream().map(Subject::getName).collect(Collectors.toList()));
        bookDto.setTitle(book.getTitle());
        bookDto.setDownloadCount(book.getDownloadCount());
        return bookDto;
    }
}

package com.library.library_web_api.webapi.services;

import com.library.library_client.contract.AuthorDto;
import com.library.library_client.contract.BookDto;
import com.library.library_data.model.*;
import com.library.library_data.repositories.ICatalogData;
import com.library.library_updater.books.mappers.IMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService{

    private final ICatalogData db;
    private final IMapper mapper;

    @Override
    public List<BookDto> getAllBooks() {
        return db.getBooks().findAll().stream().map(BookService::getBookDto).collect(Collectors.toList());
    }

    @Override
    public BookDto getBookDetails(Long id) {
        return db.getBooks().findById(id).map(BookService::getBookDto).orElse(null);
    }

    @Override
    public void editBook(BookDto bookDto) {
        Book bookEntity = mapper.book().mapToEntity(bookDto);

        bookDto.getAuthors().forEach(authorDto -> {
            Author authorEntity = db.getAuthors().findByName(authorDto.getName());
            if (authorEntity != null) {
                authorEntity.getBooks().add(bookEntity);
            }else {
                authorEntity = mapper.author().mapToEntity(authorDto);
                authorEntity.getBooks().add(bookEntity);
                db.getAuthors().save(authorEntity);
            }
            bookEntity.getAuthors().add(authorEntity);
        });

        bookDto.getLanguages().forEach(language -> {
            Language languageEntity = db.getLanguages().findByName(language);
            if (languageEntity != null) {
                languageEntity.getBooks().add(bookEntity);
            } else {
                languageEntity = mapper.language().mapToEntity(language);
                languageEntity.getBooks().add(bookEntity);
                db.getLanguages().save(languageEntity);
            }
            bookEntity.getLanguages().add(languageEntity);
        });

        bookDto.getSubjects().forEach(subject -> {
            Subject subjectEntity = db.getSubjects().findByName(subject);
            if (subjectEntity != null) {
                subjectEntity.getBooks().add(bookEntity);
            } else {
                subjectEntity = mapper.subject().mapToEntity(subject);
                subjectEntity.getBooks().add(bookEntity);
                db.getSubjects().save(subjectEntity);
            }
            bookEntity.getSubjects().add(subjectEntity);
        });

        bookDto.getBookshelves().forEach(bookshelves -> {
            Bookshelves bookshelvesEntity = db.getBookshelves().findByName(bookshelves);
            if (bookshelvesEntity != null) {
                bookshelvesEntity.getBooks().add(bookEntity);
            } else {
                bookshelvesEntity = mapper.bookshelves().mapToEntity(bookshelves);
                bookshelvesEntity.getBooks().add(bookEntity);
                db.getBookshelves().save(bookshelvesEntity);
            }
            bookEntity.getBookshelves().add(bookshelvesEntity);
        });

        db.getBooks().save(bookEntity);
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
        return bookDto;
    }
}

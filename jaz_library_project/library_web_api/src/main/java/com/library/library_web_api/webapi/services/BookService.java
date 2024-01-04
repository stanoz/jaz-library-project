package com.library.library_web_api.webapi.services;

import com.library.library_client.contract.AuthorDto;
import com.library.library_client.contract.BookDto;
import com.library.library_data.model.*;
import com.library.library_data.repositories.ICatalogData;
import com.library.library_updater.books.mappers.IMapper;
import com.library.library_web_api.webapi.contract.BookshelvesDto;
import com.library.library_web_api.webapi.contract.LanguageDto;
import com.library.library_web_api.webapi.contract.SubjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
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

    @Override
    public void editAuthor(AuthorDto authorDto, Long bookId, Long authorId) {
        db.getBooks().findById(bookId).ifPresent(book -> {
            Author author = book.getAuthors().stream().filter(a -> a.getId()==authorId).findFirst().orElse(null);
            if (author != null) {
                author.setName(authorDto.getName());
                author.setYearOfBirth(authorDto.getYearOfBirth());
                author.setYearOfDeath(authorDto.getYearOfDeath());
                db.getAuthors().save(author);
            }
        });
    }

    @Override
    public Long getAuthorId(String name, Long bookId) {
        return db.getBooks().findById(bookId)
                .map(book -> Objects.requireNonNull(book.getAuthors().stream()
                        .filter(author -> author.getName().equals(name))
                        .findFirst().orElse(null)).getId()).orElse(null);
    }

    @Override
    public void deleteAuthor(Long bookId, Long authorId) {
        db.getBooks().findById(bookId).ifPresent(book -> {
            Author author = book.getAuthors().stream().filter(a -> a.getId()==authorId).findFirst().orElse(null);
            if (author != null) {
                book.getAuthors().remove(author);
                author.getBooks().remove(book);
                db.getAuthors().save(author);
                db.getBooks().save(book);
            }
        });
    }

    @Override
    public void addAuthor(AuthorDto authorDto, Long bookId) {
        db.getBooks().findById(bookId).ifPresent(book -> {
            Author author = mapper.author().mapToEntity(authorDto);
            author.getBooks().add(book);
            db.getAuthors().save(author);
            book.getAuthors().add(author);
            db.getBooks().save(book);
        });
    }

    @Override
    public Long getSubjectId(String name, Long bookId) {
        return db.getBooks().findById(bookId)
                .map(book -> Objects.requireNonNull(book.getSubjects().stream()
                        .filter(subject -> subject.getName().equals(name))
                        .findFirst().orElse(null)).getId()).orElse(null);
    }

    @Override
    public void editSubject(SubjectDto subjectDto, Long bookId) {
        db.getBooks().findById(bookId).ifPresent(book -> {
            Subject subject = book.getSubjects().stream().filter(s -> s.getId()==subjectDto.getId()).findFirst().orElse(null);
            if (subject != null) {
                subject.setName(subjectDto.getName());
                db.getSubjects().save(subject);
            }
        });
    }

    @Override
    public void deleteSubject(Long bookId, Long subjectId) {
        db.getBooks().findById(bookId).ifPresent(book -> {
            Subject subject = book.getSubjects().stream().filter(s -> s.getId()==subjectId).findFirst().orElse(null);
            if (subject != null) {
                book.getSubjects().remove(subject);
                subject.getBooks().remove(book);
                db.getSubjects().save(subject);
                db.getBooks().save(book);
            }
        });
    }

    @Override
    public void addSubject(SubjectDto subjectDto, Long bookId) {
        db.getBooks().findById(bookId).ifPresent(book -> {
            Subject subject = mapper.subject().mapToEntity(subjectDto.getName());
            subject.getBooks().add(book);
            db.getSubjects().save(subject);
            book.getSubjects().add(subject);
            db.getBooks().save(book);
        });
    }

    @Override
    public Long getLanguageId(String name, Long bookId) {
        return db.getBooks().findById(bookId)
                .map(book -> Objects.requireNonNull(book.getLanguages().stream()
                        .filter(language -> language.getName().equals(name))
                        .findFirst().orElse(null)).getId()).orElse(null);
    }

    @Override
    public void editLanguage(LanguageDto languageDto, Long bookId) {
        db.getBooks().findById(bookId).ifPresent(book -> {
            Language language = book.getLanguages().stream().filter(l -> l.getId()==languageDto.getId()).findFirst().orElse(null);
            if (language != null) {
                language.setName(languageDto.getName());
                db.getLanguages().save(language);
            }
        });
    }

    @Override
    public void deleteLanguage(Long bookId, Long languageId) {
        db.getBooks().findById(bookId).ifPresent(book -> {
            Language language = book.getLanguages().stream().filter(l -> l.getId()==languageId).findFirst().orElse(null);
            if (language != null) {
                book.getLanguages().remove(language);
                language.getBooks().remove(book);
                db.getLanguages().save(language);
                db.getBooks().save(book);
            }
        });
    }

    @Override
    public void addLanguage(LanguageDto languageDto, Long bookId) {
        db.getBooks().findById(bookId).ifPresent(book -> {
            Language language = mapper.language().mapToEntity(languageDto.getName());
            language.getBooks().add(book);
            db.getLanguages().save(language);
            book.getLanguages().add(language);
            db.getBooks().save(book);
        });
    }

    @Override
    public Long getBookshelvesId(String name, Long bookId) {
        return db.getBooks().findById(bookId)
                .map(book -> Objects.requireNonNull(book.getBookshelves().stream()
                        .filter(bookshelves -> bookshelves.getName().equals(name))
                        .findFirst().orElse(null)).getId()).orElse(null);
    }

    @Override
    public void editBookshelves(BookshelvesDto bookshelvesDto, Long bookId) {
        db.getBooks().findById(bookId).ifPresent(book -> {
            Bookshelves bookshelves = book.getBookshelves().stream().filter(b -> b.getId()==bookshelvesDto.getId()).findFirst().orElse(null);
            if (bookshelves != null) {
                bookshelves.setName(bookshelvesDto.getName());
                db.getBookshelves().save(bookshelves);
            }
        });
    }

    @Override
    public void deleteBookshelves(Long bookId, Long bookshelvesId) {
        db.getBooks().findById(bookId).ifPresent(book -> {
            Bookshelves bookshelves = book.getBookshelves().stream().filter(b -> b.getId()==bookshelvesId).findFirst().orElse(null);
            if (bookshelves != null) {
                book.getBookshelves().remove(bookshelves);
                bookshelves.getBooks().remove(book);
                db.getBookshelves().save(bookshelves);
                db.getBooks().save(book);
            }
        });
    }

    @Override
    public void addBookshelves(BookshelvesDto bookshelvesDto, Long bookId) {
        db.getBooks().findById(bookId).ifPresent(book -> {
            Bookshelves bookshelves = mapper.bookshelves().mapToEntity(bookshelvesDto.getName());
            bookshelves.getBooks().add(book);
            db.getBookshelves().save(bookshelves);
            book.getBookshelves().add(bookshelves);
            db.getBooks().save(book);
        });
    }
}

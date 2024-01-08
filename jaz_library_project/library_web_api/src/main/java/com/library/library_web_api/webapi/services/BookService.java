package com.library.library_web_api.webapi.services;

import com.library.library_client.contract.AuthorDto;
import com.library.library_client.contract.BookDto;
import com.library.library_data.model.*;
import com.library.library_data.repositories.ICatalogData;
import com.library.library_updater.books.mappers.IMapper;
import com.library.library_web_api.webapi.contract.BookshelvesDto;
import com.library.library_web_api.webapi.contract.LanguageDto;
import com.library.library_web_api.webapi.contract.NewBookDto;
import com.library.library_web_api.webapi.contract.SubjectDto;
import com.library.library_web_api.webapi.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService{

    private final ICatalogData db;
    private final IMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<BookDto> getAllBooks() {
        return db.getBooks().findAllByOrderById().stream().map(BookService::getBookDto).toList();
    }

    @Override
    public BookDto getBookDetails(Long id) {
        return db.getBooks().findById(id).map(BookService::getBookDto).orElseThrow(() -> new NotFoundException("Book not found"));
    }

    @Override
    public void editBook(BookDto bookDto, Long id) {
        db.getBooks().findById(id).ifPresentOrElse(book -> {
            book.setTitle(bookDto.getTitle());
            book.setDownloadCount(bookDto.getDownloadCount());
            db.getBooks().save(book);
        }, () -> {
            throw new NotFoundException("Book not found");
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
        db.getBooks().findById(bookId).ifPresentOrElse(book -> {
            Author author = book.getAuthors().stream()
                    .filter(a -> a.getId()==authorId).findFirst()
                    .orElseThrow(() -> new NotFoundException("Author not found"));

                author.setName(authorDto.getName());
                author.setYearOfBirth(authorDto.getYearOfBirth());
                author.setYearOfDeath(authorDto.getYearOfDeath());
                db.getAuthors().save(author);
        }, () -> {
            throw new NotFoundException("Book not found");
        });
    }

    @Override
    public Long getAuthorId(String name, Long bookId) {
        return db.getBooks().findById(bookId)
                .map(book -> Objects.requireNonNull(book.getAuthors().stream()
                        .filter(author -> author.getName().equals(name))
                        .findFirst().orElseThrow(()-> new NotFoundException("Author nor found"))).getId())
                .orElseThrow(() -> new NotFoundException("Book not found"));
    }

    @Override
    public void deleteAuthor(Long bookId, Long authorId) {
        db.getBooks().findById(bookId).ifPresentOrElse(book -> {
            Author author = book.getAuthors().stream()
                    .filter(a -> a.getId()==authorId).findFirst().orElseThrow(() -> new NotFoundException("Author not found"));
            if (author != null) {
                book.getAuthors().remove(author);
                author.getBooks().remove(book);
                db.getAuthors().save(author);
                db.getBooks().save(book);
            }
        }, () -> {
            throw new NotFoundException("Book not found");
        });
    }

    @Override
    public void addAuthor(AuthorDto authorDto, Long bookId) {
        db.getBooks().findById(bookId).ifPresentOrElse(book -> {
            if (db.getAuthors().existsByName(authorDto.getName())) {
                Author author = db.getAuthors().findByName(authorDto.getName());
                author.getBooks().add(book);
                book.getAuthors().add(author);
            }else {
                Author author = mapper.author().mapToEntity(authorDto);
                author.getBooks().add(book);
                db.getAuthors().save(author);
                book.getAuthors().add(author);
            }
            db.getBooks().save(book);
        }, () -> {
            throw new NotFoundException("Book not found");
        });
    }

    @Override
    public Long getSubjectId(String name, Long bookId) {
        return db.getBooks().findById(bookId)
                .map(book -> Objects.requireNonNull(book.getSubjects().stream()
                        .filter(subject -> subject.getName().equals(name))
                        .findFirst().orElseThrow(() -> new NotFoundException("Subject not found"))).getId())
                .orElseThrow(() -> new NotFoundException("Book not found"));
    }

    @Override
    public void editSubject(SubjectDto subjectDto, Long bookId) {
        db.getBooks().findById(bookId).ifPresentOrElse(book -> {
            Subject subject = book.getSubjects().stream()
                    .filter(s -> s.getId()==subjectDto.getId()).findFirst()
                    .orElseThrow(() -> new NotFoundException("Subject not found"));

                subject.setName(subjectDto.getName());
                db.getSubjects().save(subject);
        }, () -> {
            throw new NotFoundException("Book not found");
        });
    }

    @Override
    public void deleteSubject(Long bookId, Long subjectId) {
        db.getBooks().findById(bookId).ifPresentOrElse(book -> {
            Subject subject = book.getSubjects().stream()
                    .filter(s -> s.getId()==subjectId).findFirst().orElseThrow(() -> new NotFoundException("Subject not found"));
            if (subject != null) {
                book.getSubjects().remove(subject);
                subject.getBooks().remove(book);
                db.getSubjects().save(subject);
                db.getBooks().save(book);
            }
        }, () -> {
            throw new NotFoundException("Book not found");
        });
    }

    @Override
    public void addSubject(SubjectDto subjectDto, Long bookId) {
        db.getBooks().findById(bookId).ifPresentOrElse(book -> {
            if (db.getSubjects().existsByName(subjectDto.getName())) {
                Subject subject = db.getSubjects().findByName(subjectDto.getName());
                subject.getBooks().add(book);
                book.getSubjects().add(subject);
            }else {
                Subject subject = mapper.subject().mapToEntity(subjectDto.getName());
                subject.getBooks().add(book);
                db.getSubjects().save(subject);
                book.getSubjects().add(subject);
            }
            db.getBooks().save(book);
        }, () -> {
            throw new NotFoundException("Book not found");
        });
    }

    @Override
    public Long getLanguageId(String name, Long bookId) {
        return db.getBooks().findById(bookId)
                .map(book -> Objects.requireNonNull(book.getLanguages().stream()
                        .filter(language -> language.getName().equals(name))
                        .findFirst().orElseThrow(() -> new NotFoundException("Language not found"))).getId())
                .orElseThrow(() -> new NotFoundException("Book not found"));
    }

    @Override
    public void editLanguage(LanguageDto languageDto, Long bookId) {
        db.getBooks().findById(bookId).ifPresentOrElse(book -> {
            Language language = book.getLanguages().stream()
                    .filter(l -> l.getId()==languageDto.getId()).findFirst()
                    .orElseThrow(() -> new NotFoundException("Language not found"));

                language.setName(languageDto.getName());
                db.getLanguages().save(language);
        }, () -> {
            throw new NotFoundException("Book not found");
        });
    }

    @Override
    public void deleteLanguage(Long bookId, Long languageId) {
        db.getBooks().findById(bookId).ifPresentOrElse(book -> {
            Language language = book.getLanguages().stream()
                    .filter(l -> l.getId()==languageId).findFirst().orElseThrow(() -> new NotFoundException("Language not found"));
            if (language != null) {
                book.getLanguages().remove(language);
                language.getBooks().remove(book);
                db.getLanguages().save(language);
                db.getBooks().save(book);
            }
        }, () -> {
            throw new NotFoundException("Book not found");
        });
    }

    @Override
    public void addLanguage(LanguageDto languageDto, Long bookId) {
        db.getBooks().findById(bookId).ifPresentOrElse(book -> {
            if (db.getLanguages().existsByName(languageDto.getName())) {
                Language language = db.getLanguages().findByName(languageDto.getName());
                language.getBooks().add(book);
                book.getLanguages().add(language);
            }else {
                Language language = mapper.language().mapToEntity(languageDto.getName());
                language.getBooks().add(book);
                db.getLanguages().save(language);
                book.getLanguages().add(language);
            }
            db.getBooks().save(book);
        }, () -> {
            throw new NotFoundException("Book not found");
        });
    }

    @Override
    public Long getBookshelvesId(String name, Long bookId) {
        return db.getBooks().findById(bookId)
                .map(book -> Objects.requireNonNull(book.getBookshelves().stream()
                        .filter(bookshelves -> bookshelves.getName().equals(name))
                        .findFirst().orElseThrow(() -> new NotFoundException("Bookshelves not found"))).getId())
                .orElseThrow(() -> new NotFoundException("Book not found"));
    }

    @Override
    public void editBookshelves(BookshelvesDto bookshelvesDto, Long bookId) {
        db.getBooks().findById(bookId).ifPresentOrElse(book -> {
            Bookshelves bookshelves = book.getBookshelves().stream()
                    .filter(b -> b.getId()==bookshelvesDto.getId()).findFirst()
                    .orElseThrow(() -> new NotFoundException("Bookshelves not found"));

                bookshelves.setName(bookshelvesDto.getName());
                db.getBookshelves().save(bookshelves);
        }, () -> {
            throw new NotFoundException("Book not found");
        });
    }

    @Override
    public void deleteBookshelves(Long bookId, Long bookshelvesId) {
        db.getBooks().findById(bookId).ifPresentOrElse(book -> {
            Bookshelves bookshelves = book.getBookshelves().stream()
                    .filter(b -> b.getId()==bookshelvesId).findFirst().orElseThrow(() -> new NotFoundException("Bookshelves not found"));
            if (bookshelves != null) {
                book.getBookshelves().remove(bookshelves);
                bookshelves.getBooks().remove(book);
                db.getBookshelves().save(bookshelves);
                db.getBooks().save(book);
            }
        }, () -> {
            throw new NotFoundException("Book not found");
        });
    }

    @Override
    public void addBookshelves(BookshelvesDto bookshelvesDto, Long bookId) {
        db.getBooks().findById(bookId).ifPresentOrElse(book -> {
            if (db.getBookshelves().existsByName(bookshelvesDto.getName())) {
                Bookshelves bookshelves = db.getBookshelves().findByName(bookshelvesDto.getName());
                bookshelves.getBooks().add(book);
                book.getBookshelves().add(bookshelves);
            }else {
                Bookshelves bookshelves = mapper.bookshelves().mapToEntity(bookshelvesDto.getName());
                bookshelves.getBooks().add(book);
                db.getBookshelves().save(bookshelves);
                book.getBookshelves().add(bookshelves);
            }
            db.getBooks().save(book);
        }, () -> {
            throw new NotFoundException("Book not found");
        });
    }

//    @Override
//    public void deleteBook(Long id) {
//        boolean exists = db.getBooks().existsById(id);
//        if (exists) {
//            db.getBooks().findById(id).ifPresent(book -> {
//                book.getAuthors().forEach(author -> {
//                    author.getBooks().remove(book);
//                    db.getAuthors().save(author);
//                });
//                book.getSubjects().forEach(subject -> {
//                    subject.getBooks().remove(book);
//                    db.getSubjects().save(subject);
//                });
//                book.getLanguages().forEach(language -> {
//                    language.getBooks().remove(book);
//                    db.getLanguages().save(language);
//                });
//                book.getBookshelves().forEach(bookshelves -> {
//                    bookshelves.getBooks().remove(book);
//                    db.getBookshelves().save(bookshelves);
//                });
//                db.getBooks().save(book);
//            });
//            db.getBooks().deleteById(id);
////            db.getBooks().deleteBookAndRelatedEntities(id);
//        }else {
//            throw new NotFoundException("Book not found");
//        }
//    }
    @Transactional
    @Override
    public void deleteBook(Long bookId) {
            if (!db.getBooks().existsById(bookId)) {
                throw new NotFoundException("Book not found");
            }
        entityManager.createNativeQuery("DELETE FROM author_books WHERE author_books.books_id = :bookId")
                .setParameter("bookId", bookId)
                .executeUpdate();

        entityManager.createNativeQuery("DELETE FROM language_books WHERE language_books.books_id = :bookId")
                .setParameter("bookId", bookId)
                .executeUpdate();

        entityManager.createNativeQuery("DELETE FROM subject_books WHERE subject_books.books_id = :bookId")
                .setParameter("bookId", bookId)
                .executeUpdate();

        entityManager.createNativeQuery("DELETE FROM bookshelves_books WHERE bookshelves_books.books_id = :bookId")
                .setParameter("bookId", bookId)
                .executeUpdate();

        entityManager.createNativeQuery("DELETE FROM book WHERE book.id = :bookId")
                .setParameter("bookId", bookId)
                .executeUpdate();
    }

    @Override
    public void addBook(NewBookDto newBookDto) {
        Book book = new Book();
        book.setTitle(newBookDto.getTitle());
        book.setDownloadCount(newBookDto.getDownloadCount());
        db.getBooks().save(book);
    }
}

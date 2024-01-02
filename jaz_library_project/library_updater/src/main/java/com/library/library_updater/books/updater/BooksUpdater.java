package com.library.library_updater.books.updater;

import com.library.library_client.IBooksClient;
import com.library.library_client.contract.BookDto;
import com.library.library_data.model.*;
import com.library.library_data.repositories.ICatalogData;
import com.library.library_updater.books.mappers.IMapper;
import com.library.tools.safeinvoker.SafeInvoking;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BooksUpdater implements IUpdateBooks{
    private final ICatalogData dbCatalog;
    private final IBooksClient booksClient;
    private final SafeInvoking invoker;
    final IMapper mapper;

    public BooksUpdater(ICatalogData dbCatalog, IBooksClient booksClient, IMapper mapper, SafeInvoking invoker) {
        this.dbCatalog = dbCatalog;
        this.booksClient = booksClient;
        this.mapper = mapper;
        this.invoker = invoker;
    }

    @Override
    public void updateByResultPages(int numberOfPages) {
        for (int i = 0; i < numberOfPages; i++) {
            int page = i + 1;
            invoker.SafeInvoke(() -> booksClient.getBooks(page).getResults().forEach(this::saveBookToDb));
        }
    }

    @Transactional
    public void saveBookToDb(BookDto bookDto) {
        Book bookEntity = mapper.book().mapToEntity(bookDto);

        bookDto.getAuthors().forEach(authorDto -> {
            Author authorEntity = dbCatalog.getAuthors().findByName(authorDto.getName());
            if (authorEntity != null) {
                authorEntity.getBooks().add(bookEntity);
            }else {
                authorEntity = mapper.author().mapToEntity(authorDto);
                authorEntity.getBooks().add(bookEntity);
                dbCatalog.getAuthors().save(authorEntity);
            }
            bookEntity.getAuthors().add(authorEntity);
        });

//        bookDto.getBookImage().forEach(bookImageDto -> {
//            BookImage bookImageEntity = dbCatalog.getBookImages().findByImageUrl(bookImageDto.getImageUrl());
//            if (bookImageEntity == null) {
//                bookImageEntity = mapper.bookImage().mapToEntity(bookImageDto);
//                bookImageEntity.setBook(bookEntity);
//                dbCatalog.getBookImages().save(bookImageEntity);
//            }else {
//                bookImageEntity.setBook(bookEntity);
//            }
//            bookEntity.getImages().add(bookImageEntity);
//        });

        bookDto.getLanguages().forEach(language -> {
            Language languageEntity = dbCatalog.getLanguages().findByName(language);
            if (languageEntity != null) {
                languageEntity.getBooks().add(bookEntity);
            } else {
                languageEntity = mapper.language().mapToEntity(language);
                languageEntity.getBooks().add(bookEntity);
                dbCatalog.getLanguages().save(languageEntity);
            }
            bookEntity.getLanguages().add(languageEntity);
        });

        bookDto.getSubjects().forEach(subject -> {
            Subject subjectEntity = dbCatalog.getSubjects().findByName(subject);
            if (subjectEntity != null) {
                subjectEntity.getBooks().add(bookEntity);
            } else {
                subjectEntity = mapper.subject().mapToEntity(subject);
                subjectEntity.getBooks().add(bookEntity);
                dbCatalog.getSubjects().save(subjectEntity);
            }
            bookEntity.getSubjects().add(subjectEntity);
        });

        bookDto.getBookshelves().forEach(bookshelves -> {
            Bookshelves bookshelvesEntity = dbCatalog.getBookshelves().findByName(bookshelves);
            if (bookshelvesEntity != null) {
                bookshelvesEntity.getBooks().add(bookEntity);
            } else {
                bookshelvesEntity = mapper.bookshelves().mapToEntity(bookshelves);
                bookshelvesEntity.getBooks().add(bookEntity);
                dbCatalog.getBookshelves().save(bookshelvesEntity);
            }
            bookEntity.getBookshelves().add(bookshelvesEntity);
        });

        dbCatalog.getBooks().save(bookEntity);
    }
}

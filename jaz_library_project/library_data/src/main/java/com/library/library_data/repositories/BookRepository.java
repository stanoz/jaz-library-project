package com.library.library_data.repositories;

import com.library.library_data.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
public interface BookRepository extends JpaRepository<Book, Long> {
//    @Transactional
//    @Modifying
//    @Query(value = "DELETE FROM author_books WHERE author_books.books_id = :bookId;" +
//            "DELETE FROM language_books WHERE language_books.books_id = :bookId;" +
//            "DELETE FROM subject_books WHERE subject_books.books_id = :bookId;" +
//            "DELETE FROM bookshelves_books WHERE bookshelves_books.books_id = :bookId;" +
//            "DELETE FROM book WHERE book.id = :bookId", nativeQuery = true)
//    void deleteBookAndRelatedEntities(@Param("bookId") Long bookId);
    List<Book> findAllByOrderById();
}

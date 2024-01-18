package com.library.library_web_api.webapi.controllers;

import com.library.library_client.contract.AuthorDto;
import com.library.library_client.contract.BookDto;
import com.library.library_web_api.webapi.contract.*;
import com.library.library_web_api.webapi.services.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookApiController {
    private final IBookService bookService;

    @GetMapping("/show-all")
    public ResponseEntity<List<BookDto>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }
    @GetMapping("/book-details/{id}")
    public ResponseEntity<BookDto> getBookDetails(@PathVariable Long id){
        return ResponseEntity.ok(bookService.getBookDetails(id));
    }
    @PutMapping("/edit-book/{id}")
    public ResponseEntity<Void> editBook(@PathVariable Long id,@RequestBody BookDto bookDto){
       bookService.editBook(bookDto, id);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/add-book")
    public ResponseEntity<Void> addBook(@RequestBody NewBookDto newBookDto){
       bookService.addBook(newBookDto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/delete-book/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
       bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/authors")
    public ResponseEntity<List<AuthorDbDto>> getAllAuthors(){
        return ResponseEntity.ok(bookService.getAllAuthors());
    }
    @GetMapping("/author/{authorId}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable Long authorId){
        return ResponseEntity.ok(bookService.getAuthor(authorId));
    }
    @GetMapping("/authors-from-book/{bookId}")
    public ResponseEntity<List<AuthorDbDto>> getAllAuthorsFromBook(@PathVariable Long bookId){
        return ResponseEntity.ok(bookService.getAllAuthorsFromBook(bookId));
    }
    @PutMapping("/edit-author/{authorId}/{bookId}")
    public ResponseEntity<Void> editAuthor(@PathVariable("authorId") Long authorId, @PathVariable("bookId") Long bookId,
                                           @RequestBody AuthorDto authorDto){
        bookService.editAuthor(authorDto, bookId, authorId);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/add-author/{bookId}")
    public ResponseEntity<Void> addAuthor(@PathVariable("bookId") Long bookId, @RequestBody AuthorDto authorDto){
        bookService.addAuthor(authorDto, bookId);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/delete-author/{bookId}/{authorId}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("bookId") Long bookId, @PathVariable("authorId") Long authorId){
       bookService.deleteAuthor(bookId, authorId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/subjects")
    public ResponseEntity<List<SubjectDto>> getAllSubjects(){
        return ResponseEntity.ok(bookService.getAllSubjects());
    }
    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<SubjectDto> getSubject(@PathVariable Long subjectId){
        return ResponseEntity.ok(bookService.getSubject(subjectId));
    }
    @GetMapping("/subjects-from-book/{bookId}")
    public ResponseEntity<List<SubjectDto>> getAllSubjectsFromBook(@PathVariable Long bookId){
        return ResponseEntity.ok(bookService.getAllSubjectsFromBook(bookId));
    }
    @PutMapping("/edit-subject/{bookId}")
    public ResponseEntity<Void> editSubject(@PathVariable("bookId") Long bookId,
                                            @RequestBody SubjectDto subjectDto){
        bookService.editSubject(subjectDto, bookId);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/add-subject/{bookId}")
    public ResponseEntity<Void> addSubject(@PathVariable("bookId") Long bookId, @RequestBody SubjectDto subjectDto){
        bookService.addSubject(subjectDto, bookId);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/delete-subject/{bookId}/{subjectId}")
    public ResponseEntity<Void> deleteSubject(@PathVariable("bookId") Long bookId, @PathVariable("subjectId") Long subjectId){
        bookService.deleteSubject(bookId, subjectId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/languages")
    public ResponseEntity<List<LanguageDto>> getAllLanguages(){
        return ResponseEntity.ok(bookService.getAllLanguages());
    }
    @GetMapping("/language/{languageId}")
    public ResponseEntity<LanguageDto> getLanguage(@PathVariable Long languageId){
        return ResponseEntity.ok(bookService.getLanguage(languageId));
    }
    @GetMapping("/languages-from-book/{bookId}")
    public ResponseEntity<List<LanguageDto>> getAllLanguagesFromBook(@PathVariable Long bookId){
        return ResponseEntity.ok(bookService.getAllLanguagesFromBook(bookId));
    }
    @PutMapping("/edit-language/{bookId}")
    public ResponseEntity<Void> editLanguage(@PathVariable("bookId") Long bookId,
                                             @RequestBody LanguageDto languageDto){
        bookService.editLanguage(languageDto, bookId);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/add-language/{bookId}")
    public ResponseEntity<Void> addLanguage(@PathVariable("bookId") Long bookId, @RequestBody LanguageDto languageDto){
       bookService.addLanguage(languageDto, bookId);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/delete-language/{bookId}/{languageId}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable("bookId") Long bookId, @PathVariable("languageId") Long languageId){
        bookService.deleteLanguage(bookId, languageId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/bookshelves")
    public ResponseEntity<List<BookshelvesDto>> getAllBookshelves(){
        return ResponseEntity.ok(bookService.getAllBookshelves());
    }
    @GetMapping("/bookshelves/{bookshelvesId}")
    public ResponseEntity<BookshelvesDto> getBookshelves(@PathVariable Long bookshelvesId){
        return ResponseEntity.ok(bookService.getBookshelves(bookshelvesId));
    }
    @GetMapping("/bookshelves-from-book/{bookId}")
    public ResponseEntity<List<BookshelvesDto>> getAllBookshelvesFromBook(@PathVariable Long bookId){
        return ResponseEntity.ok(bookService.getAllBookshelvesFromBook(bookId));
    }
    @PutMapping("/edit-bookshelves/{bookId}")
    public ResponseEntity<Void> editBookshelves(@PathVariable("bookId") Long bookId,
                                                @RequestBody BookshelvesDto bookshelvesDto){
        bookService.editBookshelves(bookshelvesDto, bookId);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/add-bookshelves/{bookId}")
    public ResponseEntity<Void> addBookshelves(@PathVariable("bookId") Long bookId, @RequestBody BookshelvesDto bookshelvesDto){
        bookService.addBookshelves(bookshelvesDto, bookId);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/delete-bookshelves/{bookId}/{bookshelvesId}")
    public ResponseEntity<Void> deleteBookshelves(@PathVariable("bookId") Long bookId, @PathVariable("bookshelvesId") Long bookshelvesId){
        bookService.deleteBookshelves(bookId, bookshelvesId);
        return ResponseEntity.noContent().build();
    }

}

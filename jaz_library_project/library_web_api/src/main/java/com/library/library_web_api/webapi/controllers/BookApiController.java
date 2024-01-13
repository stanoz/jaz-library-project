package com.library.library_web_api.webapi.controllers;

import com.library.library_client.contract.AuthorDto;
import com.library.library_client.contract.BookDto;
import com.library.library_web_api.webapi.contract.AuthorDbDto;
import com.library.library_web_api.webapi.contract.LanguageDto;
import com.library.library_web_api.webapi.contract.NewBookDto;
import com.library.library_web_api.webapi.contract.SubjectDto;
import com.library.library_web_api.webapi.services.IBookService;
import com.library.tools.safeinvoker.SafeInvoking;
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
    private final SafeInvoking invoker;

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
        invoker.SafeInvoke(() -> bookService.editBook(bookDto, id));
        return ResponseEntity.ok().build();
    }
    @PostMapping("/add-book")
    public ResponseEntity<Void> addBook(@RequestBody NewBookDto newBookDto){
        invoker.SafeInvoke(() -> bookService.addBook(newBookDto));
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/delete-book/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        invoker.SafeInvoke(() -> bookService.deleteBook(id));
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
    @PutMapping("/edit-author/{name}/{id}")
    public ResponseEntity<Void> editAuthor(@PathVariable String name, @PathVariable("id") Long bookId,
                                           @RequestBody AuthorDto authorDto){
        invoker.SafeInvoke(() -> bookService.editAuthor(authorDto, bookId, bookService.getAuthorId(name, bookId)));
        return ResponseEntity.ok().build();
    }
    @PostMapping("/add-author/{bookId}")
    public ResponseEntity<Void> addAuthor(@PathVariable("bookId") Long bookId, @RequestBody AuthorDto authorDto){
        invoker.SafeInvoke(() -> bookService.addAuthor(authorDto, bookId));
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/delete-author/{bookId}/{authorId}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("bookId") Long bookId, @PathVariable("authorId") Long authorId){
        invoker.SafeInvoke(() -> bookService.deleteAuthor(bookId, authorId));
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
        invoker.SafeInvoke(() -> bookService.editSubject(subjectDto, bookId));
        return ResponseEntity.ok().build();
    }
    @PostMapping("/add-subject/{bookId}")
    public ResponseEntity<Void> addSubject(@PathVariable("bookId") Long bookId, @RequestBody SubjectDto subjectDto){
        invoker.SafeInvoke(() -> bookService.addSubject(subjectDto, bookId));
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/delete-subject/{bookId}/{subjectId}")
    public ResponseEntity<Void> deleteSubject(@PathVariable("bookId") Long bookId, @PathVariable("subjectId") Long subjectId){
        invoker.SafeInvoke(() -> bookService.deleteSubject(bookId, subjectId));
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
        invoker.SafeInvoke(() -> bookService.editLanguage(languageDto, bookId));
        return ResponseEntity.ok().build();
    }
    @PostMapping("/add-language/{bookId}")
    public ResponseEntity<Void> addLanguage(@PathVariable("bookId") Long bookId, @RequestBody LanguageDto languageDto){
        invoker.SafeInvoke(() -> bookService.addLanguage(languageDto, bookId));
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/delete-language/{bookId}/{languageId}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable("bookId") Long bookId, @PathVariable("languageId") Long languageId){
        invoker.SafeInvoke(() -> bookService.deleteLanguage(bookId, languageId));
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/bookshelves")
    public ResponseEntity<List<SubjectDto>> getAllBookshelves(){
        return ResponseEntity.ok(bookService.getAllSubjects());
    }
    @GetMapping("/bookshelves/{bookshelvesId}")
    public ResponseEntity<SubjectDto> getBookshelves(@PathVariable Long bookshelvesId){
        return ResponseEntity.ok(bookService.getSubject(bookshelvesId));
    }
    @GetMapping("/bookshelves-from-book/{bookId}")
    public ResponseEntity<List<SubjectDto>> getAllBookshelvesFromBook(@PathVariable Long bookId){
        return ResponseEntity.ok(bookService.getAllSubjectsFromBook(bookId));
    }
    @PutMapping("/edit-bookshelves/{bookId}")
    public ResponseEntity<Void> editBookshelves(@PathVariable("bookId") Long bookId,
                                                @RequestBody SubjectDto subjectDto){
        invoker.SafeInvoke(() -> bookService.editSubject(subjectDto, bookId));
        return ResponseEntity.ok().build();
    }
    @PostMapping("/add-bookshelves/{bookId}")
    public ResponseEntity<Void> addBookshelves(@PathVariable("bookId") Long bookId, @RequestBody SubjectDto subjectDto){
        invoker.SafeInvoke(() -> bookService.addSubject(subjectDto, bookId));
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/delete-bookshelves/{bookId}/{bookshelvesId}")
    public ResponseEntity<Void> deleteBookshelves(@PathVariable("bookId") Long bookId, @PathVariable("bookshelvesId") Long bookshelvesId){
        invoker.SafeInvoke(() -> bookService.deleteSubject(bookId, bookshelvesId));
        return ResponseEntity.noContent().build();
    }

}

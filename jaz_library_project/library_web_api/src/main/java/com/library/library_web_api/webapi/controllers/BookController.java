package com.library.library_web_api.webapi.controllers;

import com.library.library_client.contract.AuthorDto;
import com.library.library_client.contract.BookDto;
import com.library.library_data.model.Author;
import com.library.library_web_api.webapi.contract.BookshelvesDto;
import com.library.library_web_api.webapi.contract.LanguageDto;
import com.library.library_web_api.webapi.contract.SubjectDto;
import com.library.library_web_api.webapi.services.IBookService;
import com.library.tools.safeinvoker.SafeInvoking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final IBookService bookService;
    private final SafeInvoking invoker;

    @GetMapping("/show-all")
    public String getAllBooks(Model model){
        List<BookDto> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books-list";
    }
    @GetMapping("/book-details/{id}")
    public String getBookDetails(Model model,@PathVariable("id") Long id){
        BookDto book = bookService.getBookDetails(id);
        model.addAttribute("book", book);
        return "book-details";
    }
    @GetMapping("/edit-book/{id}")
    public String editBook(Model model,@PathVariable("id") Long id){
        BookDto book = bookService.getBookDetails(id);
        model.addAttribute("book", book);
        return "edit-book";
    }
    @PostMapping("/edit-book/{id}")
    public String editBook(BookDto book, Model model,@PathVariable("id") Long id){
        invoker.SafeInvoke(() -> bookService.editBook(book, id));
        return "redirect:/api/books/show-all";
    }
    @GetMapping("/edit-author/{name}/{id}")
    public String editAuthor(Model model,@PathVariable("name") String name,@PathVariable("id") Long bookId){
        AuthorDto authorDto = bookService.getBookDetails(bookId).getAuthors().stream()
                .filter(a -> a.getName().equals(name)).findFirst().orElse(null);
        Long authorId = bookService.getAuthorId(name, bookId);
        model.addAttribute("bookId", bookId);
        model.addAttribute("author", authorDto);
        model.addAttribute("authorId", authorId);
        return "edit-author";
    }
    @PostMapping("/edit-author/{name}/{id}/{authorId}")
    public String editAuthor(AuthorDto authorDto, Model model,@PathVariable("name") String name,
                             @PathVariable("id") Long bookId, @PathVariable("authorId") Long authorId){
        invoker.SafeInvoke(() -> bookService.editAuthor(authorDto, bookId, authorId));
        return "redirect:/api/books/book-details/" + bookId;
    }
    @GetMapping("/delete-author/{name}/{id}")
    public String deleteAuthor(Model model,@PathVariable("name") String name,@PathVariable("id") Long bookId){
        AuthorDto authorDto = bookService.getBookDetails(bookId).getAuthors().stream()
                .filter(a -> a.getName().equals(name)).findFirst().orElse(null);
        Long authorId = bookService.getAuthorId(name, bookId);
        model.addAttribute("bookId", bookId);
        model.addAttribute("author", authorDto);
        model.addAttribute("authorId", authorId);
        return "delete-author";
    }
    @PostMapping("/delete-author/{name}/{id}/{authorId}")
    public String deleteAuthor(AuthorDto authorDto, Model model,@PathVariable("name") String name,
                             @PathVariable("id") Long bookId, @PathVariable("authorId") Long authorId){
        invoker.SafeInvoke(() -> bookService.deleteAuthor(bookId, authorId));
        return "redirect:/api/books/book-details/" + bookId;
    }
    @GetMapping("/add-author/{id}")
    public String addAuthor(Model model,@PathVariable("id") Long bookId){
        AuthorDto authorDto = new AuthorDto();
        model.addAttribute("bookId", bookId);
        model.addAttribute("author", authorDto);
        return "add-author";
    }
    @PostMapping("/add-author/{bookId}")
    public String addAuthor(AuthorDto authorDto, Model model, @PathVariable("bookId") Long bookId){
        invoker.SafeInvoke(() -> bookService.addAuthor(authorDto, bookId));
        return "redirect:/api/books/book-details/" + bookId;
    }
    @GetMapping("/edit-subject/{name}/{id}")
    public String editSubject(Model model,@PathVariable("name") String name,@PathVariable("id") Long bookId){
        Long subjectId = bookService.getSubjectId(name, bookId);
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(subjectId);
        subjectDto.setName(name);
        model.addAttribute("bookId", bookId);
        model.addAttribute("subjectDto", subjectDto);
        return "edit-subject";
    }
    @PostMapping("/edit-subject/{bookId}/{subjectId}")
    public String editSubject(SubjectDto subjectDto,Model model,@PathVariable("bookId") Long bookId,
                              @PathVariable("subjectId") Long subjectId){
        subjectDto.setId(subjectId);
        invoker.SafeInvoke(() -> bookService.editSubject(subjectDto, bookId));
        return "redirect:/api/books/book-details/" + bookId;
    }
    @GetMapping("/delete-subject/{name}/{id}")
    public String deleteSubject(Model model,@PathVariable("name") String name,@PathVariable("id") Long bookId) {
        Long subjectId = bookService.getSubjectId(name, bookId);
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(subjectId);
        subjectDto.setName(name);
        model.addAttribute("bookId", bookId);
        model.addAttribute("subjectDto", subjectDto);
        return "delete-subject";
    }
    @PostMapping("/delete-subject/{bookId}{subjectId}")
    public String deleteSubject(Model model,@PathVariable("bookId") Long bookId, @PathVariable("subjectId") Long subjectId){
        invoker.SafeInvoke(() -> bookService.deleteSubject(bookId, subjectId));
        return "redirect:/api/books/book-details/" + bookId;
    }
    @GetMapping("/add-subject/{id}")
    public String addSubject(Model model,@PathVariable("id") Long bookId){
        SubjectDto subjectDto = new SubjectDto();
        model.addAttribute("bookId", bookId);
        model.addAttribute("subjectDto", subjectDto);
        return "add-subject";
    }
    @PostMapping("/add-subject/{bookId}")
    public String addSubject(SubjectDto subjectDto, Model model, @PathVariable("bookId") Long bookId){
        invoker.SafeInvoke(() -> bookService.addSubject(subjectDto, bookId));
        return "redirect:/api/books/book-details/" + bookId;
    }
    @GetMapping("/edit-language/{name}/{id}")
    public String editLanguage(Model model,@PathVariable("name") String name,@PathVariable("id") Long bookId){
        LanguageDto languageDto = new LanguageDto();
        Long languageId = bookService.getLanguageId(name, bookId);
        languageDto.setId(languageId);
        languageDto.setName(name);
        model.addAttribute("bookId", bookId);
        model.addAttribute("languageDto", languageDto);
        return "edit-language";
    }
    @PostMapping("/edit-language/{bookId}/{languageId}")
    public String editLanguage(LanguageDto languageDto,Model model,@PathVariable("bookId") Long bookId,
                              @PathVariable("languageId") Long languageId){
        languageDto.setId(languageId);
        invoker.SafeInvoke(() -> bookService.editLanguage(languageDto, bookId));
        return "redirect:/api/books/book-details/" + bookId;
    }
    @GetMapping("/delete-language/{name}/{id}")
    public String deleteLanguage(Model model,@PathVariable("name") String name,@PathVariable("id") Long bookId) {
        LanguageDto languageDto = new LanguageDto();
        Long languageId = bookService.getLanguageId(name, bookId);
        languageDto.setId(languageId);
        languageDto.setName(name);
        model.addAttribute("bookId", bookId);
        model.addAttribute("languageDto", languageDto);
        return "delete-language";
    }
    @PostMapping("/delete-language/{bookId}/{languageId}")
    public String deleteLanguage(Model model,@PathVariable("bookId") Long bookId, @PathVariable("languageId") Long languageId){
        invoker.SafeInvoke(() -> bookService.deleteLanguage(bookId, languageId));
        return "redirect:/api/books/book-details/" + bookId;
    }
    @GetMapping("/add-language/{id}")
    public String addLanguage(Model model,@PathVariable("id") Long bookId){
        LanguageDto languageDto = new LanguageDto();
        model.addAttribute("bookId", bookId);
        model.addAttribute("languageDto", languageDto);
        return "add-language";
    }
    @PostMapping("/add-language/{bookId}")
    public String addLanguage(LanguageDto languageDto, Model model, @PathVariable("bookId") Long bookId){
        invoker.SafeInvoke(() -> bookService.addLanguage(languageDto, bookId));
        return "redirect:/api/books/book-details/" + bookId;
    }
    @GetMapping("/edit-bookshelves/{name}/{id}")
    public String editBookshelves(Model model,@PathVariable("name") String name,@PathVariable("id") Long bookId){
        Long bookshelvesId = bookService.getBookshelvesId(name, bookId);
        BookshelvesDto bookshelvesDto = new BookshelvesDto();
        bookshelvesDto.setId(bookshelvesId);
        bookshelvesDto.setName(name);
        model.addAttribute("bookId", bookId);
        model.addAttribute("bookshelvesDto", bookshelvesDto);
        return "edit-bookshelves";
    }
    @PostMapping("/edit-bookshelves/{bookId}/{bookshelvesId}")
    public String editBookshelves(BookshelvesDto bookshelvesDto,Model model,@PathVariable("bookId") Long bookId,
                              @PathVariable("bookshelvesId") Long bookshelvesId){
        bookshelvesDto.setId(bookshelvesId);
        invoker.SafeInvoke(() -> bookService.editBookshelves(bookshelvesDto, bookId));
        return "redirect:/api/books/book-details/" + bookId;
    }
    @GetMapping("/delete-bookshelves/{name}/{id}")
    public String deleteBookshelves(Model model,@PathVariable("name") String name,@PathVariable("id") Long bookId) {
        Long bookshelvesId = bookService.getBookshelvesId(name, bookId);
        BookshelvesDto bookshelvesDto = new BookshelvesDto();
        bookshelvesDto.setId(bookshelvesId);
        bookshelvesDto.setName(name);
        model.addAttribute("bookId", bookId);
        model.addAttribute("bookshelvesDto", bookshelvesDto);
        return "delete-bookshelves";
    }
    @PostMapping("/delete-bookshelves/{bookId}/{bookshelvesId}")
    public String deleteBookshelves(Model model,@PathVariable("bookId") Long bookId, @PathVariable("bookshelvesId") Long bookshelvesId){
        invoker.SafeInvoke(() -> bookService.deleteBookshelves(bookId, bookshelvesId));
        return "redirect:/api/books/book-details/" + bookId;
    }
    @GetMapping("/add-bookshelves/{id}")
    public String addBookshelves(Model model,@PathVariable("id") Long bookId){
        BookshelvesDto bookshelvesDto = new BookshelvesDto();
        model.addAttribute("bookId", bookId);
        model.addAttribute("bookshelvesDto", bookshelvesDto);
        return "add-bookshelves";
    }
    @PostMapping("/add-bookshelves/{bookId}")
    public String addBookshelves(BookshelvesDto bookshelvesDto, Model model, @PathVariable("bookId") Long bookId){
        invoker.SafeInvoke(() -> bookService.addBookshelves(bookshelvesDto, bookId));
        return "redirect:/api/books/book-details/" + bookId;
    }
}

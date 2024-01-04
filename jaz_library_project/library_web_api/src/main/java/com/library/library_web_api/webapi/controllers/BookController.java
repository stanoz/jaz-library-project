package com.library.library_web_api.webapi.controllers;

import com.library.library_client.contract.AuthorDto;
import com.library.library_client.contract.BookDto;
import com.library.library_data.model.Author;
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
        model.addAttribute("bookId", bookId);
        AuthorDto authorDto = bookService.getBookDetails(bookId).getAuthors().stream()
                .filter(a -> a.getName().equals(name)).findFirst().orElse(null);
        Long authorId = bookService.getAuthorId(name, bookId);
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
}

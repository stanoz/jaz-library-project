package com.library.library_web_api.webapi.controllers;

import com.library.library_client.contract.BookDto;
import com.library.library_web_api.webapi.services.IBookService;
import com.library.tools.safeinvoker.SafeInvoking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @GetMapping("book-details/{id}")
    public String getBookDetails(Model model,@PathVariable("id") Long id){
        BookDto book = bookService.getBookDetails(id);
        model.addAttribute("book", book);
        return "book-details";
    }
}

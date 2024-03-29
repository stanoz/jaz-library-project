package com.library.library_updater.books.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.library.library_updater.books.updater.IUpdateBooks;

@Controller()
@RequestMapping("updater")
public class UpdaterController {
    private final IUpdateBooks booksUpdater;

    public UpdaterController(IUpdateBooks booksUpdater) {
        this.booksUpdater = booksUpdater;
    }

    @GetMapping("start")
    public ResponseEntity<Void> start(
            @RequestParam int numberOfPages) {
        booksUpdater.updateByResultPages(numberOfPages);

        return ResponseEntity.ok().build();
    }

    @GetMapping("pages")
    public ResponseEntity<Void> getPages(
            @RequestParam int pageFrom, @RequestParam int pageTo) {
        booksUpdater.updateByPagesRange(pageFrom, pageTo);
        return ResponseEntity.ok().build();

    }
}

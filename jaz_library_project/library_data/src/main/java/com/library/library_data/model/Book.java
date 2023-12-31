package com.library.library_data.model;

import lombok.Data;

import java.util.List;

@Data
public class Book {
    private long id;

    private long sourceId;

    private String title;

    private int downloadCount;

    private List<Author> authors;

    private List<BookImage> images;

    private List<Language> languages;

    private List<Subject> subjects;

    private List<Bookshelves> bookshelves;
}

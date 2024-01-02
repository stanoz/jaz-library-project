package com.library.library_data.model;

import lombok.Data;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long sourceId;

    private String title;

    private int downloadCount;

    @ManyToMany(mappedBy = "books", cascade = CascadeType.ALL)
    private List<Author> authors = new ArrayList<>();

//    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
//    private List<BookImage> images = new ArrayList<>();

    @ManyToMany(mappedBy = "books", cascade = CascadeType.ALL)
    private List<Language> languages = new ArrayList<>();

    @ManyToMany(mappedBy = "books", cascade = CascadeType.ALL)
    private List<Subject> subjects = new ArrayList<>();

    @ManyToMany(mappedBy = "books", cascade = CascadeType.ALL)
    private List<Bookshelves> bookshelves = new ArrayList<>();
}

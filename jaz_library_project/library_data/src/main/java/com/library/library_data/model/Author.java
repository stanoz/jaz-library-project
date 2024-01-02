package com.library.library_data.model;

import lombok.Data;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private int yearOfBirth;

    private int yearOfDeath;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();
}

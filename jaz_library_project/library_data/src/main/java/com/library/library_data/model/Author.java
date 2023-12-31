package com.library.library_data.model;

import lombok.Data;

@Data
public class Author {
    private long id;

    private String name;

    private int yearOfBirth;

    private int yearOfDeath;
}

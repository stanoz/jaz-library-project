package com.library.library_data.model;

import lombok.Data;
import jakarta.persistence.*;


@Entity
@Data
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
}

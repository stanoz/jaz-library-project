package com.library.library_web_api.webapi.contract;

import lombok.Data;

@Data
public class AuthorDbDto {
    private Long id;
    private String name;
    private int yearOfBirth;
    private int yearOfDeath;
}

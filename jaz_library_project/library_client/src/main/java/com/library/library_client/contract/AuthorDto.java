package com.library.library_client.contract;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthorDto {

    @JsonProperty("birth_year")
    private int yearOfBirth;

    @JsonProperty("death_year")
    private int yearOfDeath;

    @JsonProperty("name")
    private String name;
}

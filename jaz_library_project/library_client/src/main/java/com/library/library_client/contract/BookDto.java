package com.library.library_client.contract;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BookDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("authors")
    private List<AuthorDto> authors;

    @JsonProperty("subjects")
    private List<String> subjects;

    @JsonProperty("bookshelves")
    private List<String> bookshelves;

    @JsonProperty("languages")
    private List<String> languages;

//    @JsonProperty("formats")
//    private List<BookImageDto> bookImage;

    @JsonProperty("download_count")
    private int downloadCount;
}

package com.library.library_client.contract;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ResultsDto {

    @JsonProperty("count")
    private int count;

    @JsonProperty("results")
    private List<BookDto> results;
}
